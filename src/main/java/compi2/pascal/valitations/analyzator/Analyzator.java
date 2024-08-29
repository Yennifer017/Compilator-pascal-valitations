
package compi2.pascal.valitations.analyzator;

import compi2.pascal.valitations.analysis.Lexer;
import compi2.pascal.valitations.analysis.Parser;
import compi2.pascal.valitations.analysis.typet.TypeTable;
import compi2.pascal.valitations.semantic.ast.Statement;
import compi2.pascal.valitations.semantic.module.FunctionDec;
import compi2.pascal.valitations.semantic.module.ProcedureDec;
import compi2.pascal.valitations.semantic.obj.DefAst;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author blue-dragon
 */
@Getter @Setter
public class Analyzator {
    
    private List<String> semanticErrors;
    private TypeTable typeTable;    
    
    public Analyzator(){
        semanticErrors = new ArrayList<>();
        typeTable = new TypeTable();
    }
    
    /**
     * Comprueba el codigo generado por el usuario
     */
    public String comprobate(String text){
        StringBuilder builder =  new StringBuilder();
        Lexer lexer = new Lexer(new StringReader(text));
        Parser parser = new Parser(lexer, this);
        try {
            parser.parse();
            builder.append(getErrors("ERRORES LEXICOS", lexer.getErrors()));
            builder.append(getErrors("ERRORES SINTACTICOS", parser.getSyntaxErrors()));
            //TODO: ADD VALITATIONS
            //builder.append(getErrors("ERRORES SEMANTICOS", semanticErrors));
        } catch (Exception e) {
            builder.append("Error inesperado:\n");
            builder.append(e);
        }
        return builder.toString();
    }
    
    private String getErrors(String title, List<String> errorsList){
        StringBuilder builder = new StringBuilder(title);
        builder.append("\n");
        builder.append("---------------------------------------");
        builder.append("\n");
        if(errorsList.isEmpty()){
            builder.append("No hay errores\n\n");
        } else {
            for (String error : errorsList) {
                builder.append(error);
                builder.append("\n");
            }
        }
        return builder.toString();
    }
    
    /**
     *  Valida el codigo generado, a partir de los ast
     * @param types
     * @param consts
     * @param variables
     * @param functions
     * @param procedures
     * @param statements
     */
    public void semanticAnalysis(List<DefAst> types, List<DefAst> consts, List<DefAst> variables, 
            List<FunctionDec> functions, List<ProcedureDec> procedures, List<Statement> statements){
        System.out.println("Realizar el analisis semantico");
    }
    
    
}
