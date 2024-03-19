import java.util.HashMap;
import java.util.Map;

public class Interpreter extends Calculator_InfixToSufixBaseVisitor<String> {

   private Map<String, String> memory = new HashMap<String, String>();

   @Override public String visitProgram(Calculator_InfixToSufixParser.ProgramContext ctx) {
      String res = null;
      for(Calculator_InfixToSufixParser.StatContext statCtx : ctx.stat()) {
         try {
            res = visit(statCtx);
         } catch (Exception e) {
            System.out.println(e.getMessage());
         }
      }
      return res; // or you can return null; if you don't need the last result
   }

   @Override public String visitPrintExpr(Calculator_InfixToSufixParser.PrintExprContext ctx) {
      String value = visit(ctx.expr());
      System.out.println(value);
      return value;
   }

   @Override public String visitAssign(Calculator_InfixToSufixParser.AssignContext ctx) {
      String id = ctx.ID().getText();
      String value = visit(ctx.expr());
      memory.put(id, value);
      System.out.println(id + " = " + value);
      return id + " = " + value;
   }

   @Override public String visitExprUnaryOp(Calculator_InfixToSufixParser.ExprUnaryOpContext ctx) {
      String number = visit(ctx.expr());
      if (number == null) {
         throw new RuntimeException("One of the expressions returned null");
      }

      return number + (ctx.op.getText().equals("+") ? " !+" : " !-");
   }

   @Override
   public String visitExprMulDivMod(Calculator_InfixToSufixParser.ExprMulDivModContext ctx) {
      String left = visit(ctx.expr(0)); 
      String right = visit(ctx.expr(1));

      return left + " " + right + " " + ctx.op.getText();
   }

   @Override
   public String visitExprAddSub(Calculator_InfixToSufixParser.ExprAddSubContext ctx) {
      String left = visit(ctx.expr(0)); 
      String right = visit(ctx.expr(1)); 

      return left + " " + right + " " + ctx.op.getText();
   }

   @Override
   public String visitExprParent(Calculator_InfixToSufixParser.ExprParentContext ctx) {
      return visit(ctx.expr()); // directly return the result of the inner expression
   }

   @Override
   public String visitExprInteger(Calculator_InfixToSufixParser.ExprIntegerContext ctx) {
      return ctx.Integer().getText(); // Return the text of the Integer token directly
   }

   @Override public String visitExprID(Calculator_InfixToSufixParser.ExprIDContext ctx) {
      String id = ctx.ID().getText();
      if (memory.containsKey(id)) {
         return memory.get(id);
      } else {
         System.err.println("Unknown variable: " + id + " (" + id + " = null)");
         return null;
      }
   }
}
