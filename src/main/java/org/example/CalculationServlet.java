package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//urlPatterns,init(), destroy(), and service(), deploy, get header,requestParam,pathParam, get body, postman,inject service, exception handling,
@WebServlet(name = "CalculationServlet", urlPatterns = {"/calculator"})
public class CalculationServlet extends HttpServlet {

    private ApplicationService applicationService;
    private CalculationRepository calculationRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        applicationService = new ApplicationService(new CalculatorServiceImpl());
        calculationRepository = new CalculationRepository();
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
        int result = 0;
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
            calculationRepository.saveCalculationResult(a, b, result, calculationMethod);
            List<CalculationResultDTO> calculationResults = calculationRepository.getCalculationResult();
            writer.write("""
                    {
                            "result":""" + calculationResults + """
                                
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String calculationMethod = req.getHeader("x-calculation-method");
        int a = Integer.parseInt(req.getParameter("a"));
        int b = Integer.parseInt(req.getParameter("b"));
        int id = Integer.parseInt(req.getParameter("id"));
        int result = 0;
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
            calculationRepository.updateCalculationResult(id, a, b, result, calculationMethod);
            writer.write("OK");
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
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        PrintWriter writer = resp.getWriter();
        try {
            calculationRepository.deleteCalculationResult(id);
            writer.write("OK");
        } catch (Exception e) {
            writer.write("""
                    {
                        "errorMessage":""" + e.getMessage() + """
                    }                    
                    """);
        }

    }

}
