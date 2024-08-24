/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compi2.pascal.valitations.semantic.expr;

/**
 *
 * @author blue-dragon
 */
public class Operation extends Expression{
    private Expression leftExp;
    private Expression rightExp;

    @Override
    public boolean isLeaf() {
        return this.leftExp == null && this.rightExp == null;
    }

    @Override
    public boolean isComplex() {
        return this.leftExp != null || this.rightExp != null;
    }
}
