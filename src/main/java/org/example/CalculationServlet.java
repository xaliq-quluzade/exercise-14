package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

//urlPatterns,init(), destroy(), and service(), deploy, get header,requestParam,pathParam, get body, postman,inject service, exception handling,
@WebServlet(name = "CalculationServlet", urlPatterns = {"/calculator"})
public class CalculationServlet extends HttpServlet {

    private ApplicationService applicationService;

    @Override
    public void init() throws ServletException {
        super.init();
        applicationService = new ApplicationService(new CalculatorServiceImpl());
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String calculationMethod = req.getHeader("x-calculation-method");
        int a = Integer.parseInt(req.getParameter("a"));
        int b = Integer.parseInt(req.getParameter("b"));
        double result = 0;
        PrintWriter writer = resp.getWriter();

        try {
            if (calculationMethod.equalsIgnoreCase("multiply")) {
                result = applicationService.multiply(a, b);
            } else if (calculationMethod.equalsIgnoreCase("subtract")) {
                result = applicationService.subtract(a, b);
            } else if (calculationMethod.equalsIgnoreCase("add")) {
                result = applicationService.add(a, b);
            } else if (calculationMethod.equalsIgnoreCase("divide")) {
                result = applicationService.divide(a, b);
            } else {
                throw new Exception("request header-de metod duzgun daxil edilmeyib");
            }
            writer.write("""
                    {
                            "result":""" + result + """
                                
                          }
                    """);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writer.write("""
                    {
                        "errorMessage":""" + e.getMessage() + """
                    }                    
                    """);
        }
        resp.setContentType("application/json");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader bufferedReader = req.getReader();
        PrintWriter writer = resp.getWriter();
        int lineLine;
        while ((lineLine = bufferedReader.read()) != -1) {
            char chr = (char) lineLine;
            writer.write(chr);
        }
        writer.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
