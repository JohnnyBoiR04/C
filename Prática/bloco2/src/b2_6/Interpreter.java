import java.util.HashMap;
import java.util.Map;

public class Interpreter extends CalculatorBaseVisitor<String> {

   private Map<String, String> memory = new HashMap<String, String>();

   @Override public String visitProgram(CalculatorParser.ProgramContext ctx) {
      String res = null;
      for(CalculatorParser.StatContext statCtx : ctx.stat()) {
         try {
            res = visit(statCtx);
         } catch (Exception e) {
            System.out.println(e.getMessage());
         }
      }
      return res; // or you can return null; if you don't need the last result
   }

   @Override public String visitPrintExpr(CalculatorParser.PrintExprContext ctx) {
      String value = visit(ctx.expr());
      return value;
   }

   @Override public String visitAssign(CalculatorParser.AssignContext ctx) {
      String id = ctx.ID().getText();
      String value = visit(ctx.expr());
      memory.put(id, value);
      System.out.println("Variable " + id + " set to " + value);
      return value;
   }

   @Override public String visitExprUnaryOp(CalculatorParser.ExprUnaryOpContext ctx) {
      String number = visit(ctx.expr());
      if (number == null) {
         throw new RuntimeException("One of the expressions returned null");
      }

      int value = Integer.parseInt(number.trim());
      if (ctx.op.getText() != null && ctx.op.getText().equals("-")) {
         value = -value;
      }

      return String.valueOf(value);
   }

   @Override
   public String visitExprMulDivMod(CalculatorParser.ExprMulDivModContext ctx) {
      String left = visit(ctx.expr(0)); 
      String right = visit(ctx.expr(1));

      if (left == null || right == null) {
         throw new RuntimeException("One of the expressions returned null");
      }

      int leftNum = Integer.parseInt(left.trim());
      int rightNum = Integer.parseInt(right.trim());

      int result;
      switch (ctx.op.getText()) {
         case "*":
            result = leftNum * rightNum;
            break;
         case "/":
            if (rightNum == 0) throw new ArithmeticException("Division by zero");
            result = leftNum / rightNum;
            break;
         case "%":
            result = leftNum % rightNum;
            break;
         default:
            throw new RuntimeException("Unknown operator: " + ctx.op.getText());
      }

      System.out.println("Result: " + result);
      return String.valueOf(result);
   }

   @Override
   public String visitExprAddSub(CalculatorParser.ExprAddSubContext ctx) {
      String left = visit(ctx.expr(0)); 
      String right = visit(ctx.expr(1)); 

      if (left == null || right == null) {
         throw new RuntimeException("One of the expressions returned null");
      }

      int leftNum = Integer.parseInt(left.trim());
      int rightNum = Integer.parseInt(right.trim());

      int result;
      switch (ctx.op.getText()) {
         case "+":
            result = leftNum + rightNum;
            break;
         case "-":
            result = leftNum - rightNum;
            break;
         default:
            throw new RuntimeException("Unknown operator: " + ctx.op.getText());
      }

      System.out.println("Result: " + result);
      return String.valueOf(result);
   }

   @Override
   public String visitExprParent(CalculatorParser.ExprParentContext ctx) {
      return visit(ctx.expr()); // directly return the result of the inner expression
   }

   @Override
   public String visitExprInteger(CalculatorParser.ExprIntegerContext ctx) {
      return ctx.Integer().getText(); // Return the text of the Integer token directly
   }

   @Override public String visitExprID(CalculatorParser.ExprIDContext ctx) {
      String id = ctx.ID().getText();
      if (memory.containsKey(id)) {
         return memory.get(id);
      } else {
         System.err.println("Unknown variable: " + id + " (" + id + " = null)");
         return null;
      }
   }
}
