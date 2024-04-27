import org.stringtemplate.v4.ST;

public class Interpreter extends CSV2HTMLBaseVisitor<ST> {

   // Template para uma tabela HTML completa
   private ST htmlTemplate = new ST("<table>\n$header$ $rows$</table>");

   // Template para o cabeçalho da tabela HTML
   private ST headerTemplate = new ST("<tr>$headers$</tr>\n");

   // Template para uma linha da tabela HTML
   private ST rowTemplate = new ST("<tr>$values$</tr>\n");

   // Template para uma célula da tabela HTML
   private ST cellTemplate = new ST("<td>$value$</td>");

   @Override public ST visitCsvFile(CSV2HTMLParser.CsvFileContext ctx) {
      ST header = visit(ctx.hdr());
      htmlTemplate.add("header", header);

      // Constrói todas as linhas da tabela
      for (CSV2HTMLParser.RowContext rowCtx : ctx.row()) {
         ST row = visit(rowCtx);
         htmlTemplate.add("rows", row.render());
      }

      return htmlTemplate; // Retorna a tabela HTML completa
   }

   @Override public ST visitHdr(CSV2HTMLParser.HdrContext ctx) {
      return visitRow(ctx.row()); // O cabeçalho é tratado como uma linha comum
   }

   @Override public ST visitRow(CSV2HTMLParser.RowContext ctx) {
      ST row = new ST(rowTemplate);

      // Constrói todas as células da linha
      for (CSV2HTMLParser.ValueContext valueCtx : ctx.value()) {
         ST cell = visit(valueCtx);
         row.add("values", cell.render());
      }

      return row; // Retorna a linha da tabela HTML
   }

   @Override public ST visitValue(CSV2HTMLParser.ValueContext ctx) {
      ST cell = new ST(cellTemplate);

      // O valor da célula é o texto dentro da regra "value"
      // Isso lida com o texto sem aspas e strings entre aspas
      if (ctx.TEXT() != null) {
         cell.add("value", ctx.TEXT().getText());
      } else if (ctx.STRING() != null) {
         // Remove as aspas do início e do fim e substitui "" por "
         String str = ctx.STRING().getText();
         str = str.substring(1, str.length() - 1).replace("\"\"", "\"");
         cell.add("value", str);
      }

      return cell; // Retorna a célula da tabela HTML
   }
}
