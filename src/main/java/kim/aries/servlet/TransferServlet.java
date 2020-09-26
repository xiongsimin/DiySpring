package kim.aries.servlet;

import kim.aries.factory.JdkProxyFactory;
import kim.aries.service.TransferService;
import kim.aries.service.impl.TransferServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @Author aries
 * @Data 2020-09-26
 */
@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        float money = Float.parseFloat(String.valueOf(req.getParameter("money")));
        TransferService transferService = (TransferService) JdkProxyFactory.getInstance().getProxyObj(new TransferServiceImpl());
        PrintWriter writer = null;
        resp.setCharacterEncoding("utf-8");
        writer = resp.getWriter();
        try {
            transferService.doTransfer(from, to, money);
            resp.setStatus(200);
            writer.println("转账成功");
        } catch (Exception e) {
            resp.setStatus(500);
            writer.println("转账失败，" + e);
        } finally {
            writer.flush();
            writer.close();
        }
    }
}
