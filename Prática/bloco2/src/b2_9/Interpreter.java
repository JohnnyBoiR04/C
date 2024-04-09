import java.util.Map;
import java.util.HashMap;

@SuppressWarnings("CheckReturnValue")
public class Interpreter extends FractionCalculatorBaseVisitor<String> {

   private Map<String, Fraction> variables = new HashMap<>();

   @Override public String visitProgram(FractionCalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public String visitPrintExpr(FractionCalculatorParser.PrintExprContext ctx) {
      for (FractionCalculatorParser.ExprContext expr : ctx.expr()) {
         System.out.println(visit(expr));
      }
      return null;
   }

   @Override public String visitReduceExpr(FractionCalculatorParser.ReduceExprContext ctx) {
      for (FractionCalculatorParser.ExprContext expr : ctx.expr()) {
         Fraction fraction = Fraction.fromString(visit(expr));
         fraction.reduce();
         System.out.println(fraction.toString());
      }
      return null;
   }

   @Override public String visitAssign(FractionCalculatorParser.AssignContext ctx) {
      String id = ctx.ID().getText();
      for (FractionCalculatorParser.ExprContext expr : ctx.expr()) {
         variables.put(id, Fraction.fromString(visit(expr)));
      }
      return null;
   }

   @Override public String visitExprAddSub(FractionCalculatorParser.ExprAddSubContext ctx) {
      Fraction left = Fraction.fromString(visit(ctx.expr(0)));
      Fraction right = Fraction.fromString(visit(ctx.expr(1)));
      String op = ctx.op.getText();
      switch (op) {
         case "+":
            return left.add(right).toString();
         case "-":
            return left.subtract(right).toString();
         default:
            return null;
      }
   }

   @Override public String visitUnaryOp(FractionCalculatorParser.UnaryOpContext ctx) {
      Fraction fraction = Fraction.fromString(visit(ctx.expr()));
      String op = ctx.op.getText();
      switch (op) {
         case "+":
            return fraction.toString();
         case "-":
            return fraction.negate().toString();
         default:
            return null;
      }
   }

   @Override public String visitExprMulDiv(FractionCalculatorParser.ExprMulDivContext ctx) {
      Fraction left = Fraction.fromString(visit(ctx.expr(0)));
      Fraction right = Fraction.fromString(visit(ctx.expr(1)));
      String op = ctx.op.getText();
      switch (op) {
         case "*":
            return left.multiply(right).toString();
         case ":":
            return left.divide(right).toString();
         default:
            return null;
      }
   }

   @Override public String visitUnaryDenominator(FractionCalculatorParser.UnaryDenominatorContext ctx) {
      int numerator = Integer.parseInt(ctx.Integer().getText());
      return new Fraction(numerator, 1).toString();
   }

   @Override public String visitExprParent(FractionCalculatorParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }

   @Override public String visitFraction(FractionCalculatorParser.FractionContext ctx) {
      int numerator = Integer.parseInt(ctx.Integer(0).getText());
      int denominator = Integer.parseInt(ctx.Integer(1).getText());
      Fraction fraction = new Fraction(numerator, denominator);
      return fraction.toString();
   }

   @Override public String visitExprPower(FractionCalculatorParser.ExprPowerContext ctx) {
      Fraction base = Fraction.fromString(visit(ctx.expr()));
      int exponent = Integer.parseInt(ctx.Integer().getText());
      return base.power(exponent).toString();
   }

   @Override public String visitExprID(FractionCalculatorParser.ExprIDContext ctx) {
      String id = ctx.ID().getText();
      if (variables.containsKey(id)) {
         return variables.get(id).toString();
      } else {
         throw new IllegalArgumentException("Variable " + id + " not found");
      }
   }
}
