package org.akab.engine.core.annotation.processor.client;

import org.akab.engine.annotations.processor.utils.*;
import org.akab.engine.core.api.client.annotations.Request;
import org.akab.engine.core.api.client.request.ClientRequest;
import org.akab.engine.core.api.shared.extension.Contribution;

public class ContributionRequestSourceWriter extends JavaSourceWriter{

    private final JavaSourceBuilder sourceBuilder;
    private final String targetPackage;
    private final String generatedClassName;
    private final String presenter;
    private final String contributionName;
    private final FullClassName fullClassName;
    private final String contextName;
    private final FullClassName contextFullName;
    private final String contextSimpleName;
    private final String presenterSimpleName;
    private final String presenterMethod;


    public ContributionRequestSourceWriter(ProcessorElement processorElement, String presenterName,
                                           String targetPackage, String generatedClassName, String presenterMethod) {
        super(processorElement);
        this.presenter=presenterName;
        this.targetPackage=targetPackage;
        this.generatedClassName=generatedClassName;
        this.sourceBuilder=new JavaSourceBuilder(generatedClassName);

        this.contributionName = processorElement.getInterfaceFullQualifiedGenericName(Contribution.class);
        this.fullClassName = new FullClassName(contributionName);
        this.contextName = fullClassName.allImports().get(1);
        this.contextFullName = new FullClassName(contextName);
        this.contextSimpleName = contextFullName.asSimpleName();
        this.presenterSimpleName=new FullClassName(presenterName).asSimpleName();
        this.presenterMethod=presenterMethod;
    }

    @Override
    public String write() {
        this.sourceBuilder.onPackage(targetPackage)
                .imports(ClientRequest.class.getCanonicalName())
                .imports(Request.class.getCanonicalName())
                .annotate("@Request")
                .withModifiers(new ModifierBuilder().asPublic())
                .extend(ClientRequest.class.getCanonicalName()+"<"+presenter+">");
        writeBody();
        return this.sourceBuilder.build();
    }

    private void writeBody() {
        FieldBuilder fieldBuilder=this.sourceBuilder.field("extensionPoint");
        fieldBuilder.withModifier(new ModifierBuilder().asPrivate())
                .ofType(contextName)
                .end();

        ConstructorBuilder constructorBuilder=this.sourceBuilder.constructor(generatedClassName);
        constructorBuilder.withModifier(new ModifierBuilder().asPublic())
                .takes(contextName, "extensionPoint")
                .line("this.extensionPoint=extensionPoint;")
                .end();

        MethodBuilder methodBuilder=this.sourceBuilder.method("process");
        methodBuilder.annotate("@Override")
                .withModifier(new ModifierBuilder().asProtected())
                .returnsVoid()
                .takes(presenter,"presenter")
                .line("\tpresenter."+presenterMethod+"(extensionPoint.context());")
                .end();

    }
}
