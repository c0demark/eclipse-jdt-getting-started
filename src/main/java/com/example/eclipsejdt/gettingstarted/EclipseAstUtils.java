package com.example.eclipsejdt.gettingstarted;

import java.io.IOException;
import java.nio.file.Path;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.StringLiteral;

public class EclipseAstUtils {

	public static void doAstOnJavaSurceCode(Path filePath) {
		try {
			String fileContent = FileOperationUtils.getFileConetent(filePath);
			ASTParser parser = ASTParser.newParser(AST.JLS11);
//			Document document = new Document(fileContent);
//			parser.setSource(document.get().toCharArray());
			parser.setSource(fileContent.toCharArray());
			parser.setKind(ASTParser.K_COMPILATION_UNIT);
			final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
			cu.accept(new ASTVisitor() {
				@SuppressWarnings("unchecked")
				@Override
				public boolean visit(MethodDeclaration node) {
					AST ast = node.getAST();
			        MethodInvocation methodInvocation = ast.newMethodInvocation();
					QualifiedName qName = ast.newQualifiedName(ast.newSimpleName("System"), ast.newSimpleName("out"));
					methodInvocation.setExpression(qName);
					methodInvocation.setName(ast.newSimpleName("println"));
					StringLiteral literal = ast.newStringLiteral();
					literal.setLiteralValue("Hello, World");
					methodInvocation.arguments().add(literal);
					// Append the statement
					node.getBody().statements().add(ast.newExpressionStatement(methodInvocation));
					System.out.println(node.toString());
					return super.visit(node);
				}
			});
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
