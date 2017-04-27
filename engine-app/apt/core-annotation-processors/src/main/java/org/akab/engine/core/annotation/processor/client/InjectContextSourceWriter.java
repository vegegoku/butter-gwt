package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.annotations.processor.utils.*;
import org.akab.engine.core.api.client.annotations.AutoRequest;
import org.akab.engine.core.api.client.annotations.Contribute;
import org.akab.engine.core.api.shared.extension.Contribution;

public class InjectContextSourceWriter extends JavaSourceWriter {

    private final String presenter;
    private final String extensionPoint;
    private final JavaSourceBuilder sourceBuilder;
    private final String targetPackage;
    private final FullClassName presenterFullClassName;
    private final FullClassName extensionPointFullClassName;


    public InjectContextSourceWriter(ProcessorElement processorElement, String presenter,
                                     String extensionPoint, String targetPackage, String className) {
        super(processorElement);
        this.presenter=presenter;
        this.extensionPoint=extensionPoint;
        this.sourceBuilder=new JavaSourceBuilder(className);
        this.targetPackage=targetPackage;

        this.presenterFullClassName = new FullClassName(presenter);
        this.extensionPointFullClassName=new FullClassName(extensionPoint);
    }

    @Override
    public String write() {
        this.sourceBuilder.onPackage(targetPackage)
                .imports(Contribute.class.getCanonicalName())
                .imports(AutoRequest.class.getCanonicalName())
                .imports(Contribution.class.getCanonicalName())
                .imports(extensionPoint)
                .imports(presenter)
                .imports(makeRequestClassName())
                .annotate("@Contribute")
                .annotate("@AutoRequest(presenters={"+presenterFullClassName.asSimpleName()+".class}, method=\""+processorElement.simpleName()+"\")")
                .withModifiers(new ModifierBuilder().asPublic())
                .implement(Contribution.class.getCanonicalName()+"<"+extensionPoint+">");
        writeBody();
        String result=this.sourceBuilder.build();
        return result;
    }

    private void writeBody() {
        MethodBuilder methodBuilder=this.sourceBuilder.method("contribute");
        methodBuilder.annotate("@Override")
                .withModifier(new ModifierBuilder().asPublic())
                .returnsVoid()
                .takes(extensionPoint,"extensionPoint")
                .line("\tnew "+new FullClassName(makeRequestClassName()).asSimpleName()+"(extensionPoint).send();")
                .end();
    }

    private String makeRequestClassName() {
        return targetPackage.replace("contributions","requests")+"."+
                "Obtain"+extensionPointFullClassName.asSimpleName()+
                "For"+presenterFullClassName.asSimpleName()+
                "ClientRequest";
    }
}
