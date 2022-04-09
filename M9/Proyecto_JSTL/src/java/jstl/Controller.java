/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jstl;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author eduardo
 */
public class Controller extends HttpServlet {
    private ArrayList<edu.fje.daw2.Proyecto> llistatProjectes;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
HttpSession sessio = request.getSession();

        String valueButton = request.getParameter("envia");

        if(valueButton.equals("Visualitza")){
            if (sessio.getAttribute("llistatProjectes")==null) {
                llistatProjectes = new ArrayList<>();
            }else {
                llistatProjectes = (ArrayList<edu.fje.daw2.Proyecto>) sessio.getAttribute("llistatProjectes");
            }

            ArrayList<edu.fje.daw2.Proyecto> llistatProjectes = procesaXML("gantt.xml");

            sessio.setAttribute("llistatProjectes", llistatProjectes);

            RequestDispatcher rd = request.getRequestDispatcher("vistaProyectos.jsp");
            rd.forward(request, response);
        }else if(valueButton.equals("Edita")){
            ArrayList<edu.fje.daw2.Proyecto> llistatProjectes = procesaXML("gantt.xml");

            sessio.setAttribute("llistatProjectes", llistatProjectes);

            RequestDispatcher rd = request.getRequestDispatcher("editaProyectos.jsp");
            rd.forward(request, response);
        }else{
            ArrayList<edu.fje.daw2.Proyecto> llistatProjectes = null;
            try {
                llistatProjectes = procesaFORM(request, response);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            sessio.setAttribute("llistatProjectes", llistatProjectes);

            RequestDispatcher rd = request.getRequestDispatcher("vistaProyectos.jsp");
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>



  

    private ArrayList<edu.fje.daw2.Proyecto> procesaFORM(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String[] Name = request.getParameterValues("nameP");
        String[] Desc = request.getParameterValues("descP");
        String[] DataI = request.getParameterValues("data_iniP");
        String[] DataF = request.getParameterValues("data_finP");

        ArrayList<edu.fje.daw2.Proyecto> llistatProjectes = new ArrayList<edu.fje.daw2.Proyecto>();
        for(int s=0; s<Name.length ; s++) {
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"/*"EEE MMM dd HH:mm:ss zzz yyyy"*/);
            Date d = format.parse(DataI[s]);
            Date dd = format.parse(DataF[s]);
            //Date d = new Date(new Long(DataI[s]));
            //Date dd = new Date(new Long(DataF[s]));

            edu.fje.daw2.Proyecto p = new edu.fje.daw2.Proyecto(Name[s], 0, new Integer(Desc[s]), d, dd);

            String[] NameT = request.getParameterValues("name" + Name[s]);
            String[] DescT = request.getParameterValues("desc" + Name[s]);
            String[] DataIT = request.getParameterValues("data_ini" + Name[s]);
            String[] DataFT = request.getParameterValues("data_fin" + Name[s]);

            for (int r=0 ; r<NameT.length ; r++) {
                Date f = format.parse(DataIT[r]);
                Date ff = format.parse(DataFT[r]);
                //Date f = new Date(new Long(DataIT[r]));
                //Date ff = new Date(new Long(DataFT[r]));

                edu.fje.daw2.Tarea t = new edu.fje.daw2.Tarea(NameT[r], new Integer(DescT[r]), f, ff);
                p.addTasca(t);
            }
            llistatProjectes.add(p);
        }
        return llistatProjectes;
    }

    private ArrayList<edu.fje.daw2.Proyecto> procesaXML(String file) {
        ArrayList<edu.fje.daw2.Proyecto> llistatProjectes = new ArrayList<edu.fje.daw2.Proyecto>();
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

            InputStream is = getServletContext().getResourceAsStream("/WEB-INF/"+file);
            Document doc = docBuilder.parse (new InputSource(is));

            doc.getDocumentElement ().normalize ();
            NodeList llistaProjectes = doc.getElementsByTagName("projecte");
            int totalProjectes = llistaProjectes.getLength();

            for(int s=0; s<llistaProjectes.getLength() ; s++){
                Node projecte = llistaProjectes.item(s);
                if(projecte.getNodeType() == Node.ELEMENT_NODE){
                    Element projecteElement = (Element)projecte;
                    String name = projecteElement.getAttribute("name");
                    int codi = Integer.parseInt(projecteElement.getAttribute("codi"));
                    int desc = Integer.parseInt(projecteElement.getAttribute("desc"));
                    //-------
                    NodeList firstNameList = projecteElement.getElementsByTagName("FromDate");
                    Element firstNameElement = (Element)firstNameList.item(0);
                    NodeList textFNList = firstNameElement.getChildNodes();
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                    Date data_ini = format.parse(((Node)textFNList.item(0)).getNodeValue().trim());
                    //-------
                    NodeList lastNameList = projecteElement.getElementsByTagName("ToDate");
                    Element lastNameElement = (Element)lastNameList.item(0);
                    NodeList textLNList = lastNameElement.getChildNodes();
                    Date data_fin = format.parse(((Node)textLNList.item(0)).getNodeValue().trim());
                    //-------
                    edu.fje.daw2.Proyecto pj = new edu.fje.daw2.Proyecto(name, codi, desc, data_ini.getTime(), data_fin.getTime());
                    NodeList llistaTasques = projecteElement.getElementsByTagName("tasca");

                    for(int t=0; t<llistaTasques.getLength() ; t++){
                        Node tarea = llistaTasques.item(t);
                        if(tarea.getNodeType() == Node.ELEMENT_NODE){
                            Element e = (Element)tarea;
                            name = e.getAttribute("name");
                            data_fin = format.parse(e.getAttribute("data_fin"));
                            data_ini = format.parse(e.getAttribute("data_ini"));
                            desc = Integer.parseInt(e.getAttribute("desc"));
                            edu.fje.daw2.Tarea tasca = new edu.fje.daw2.Tarea(name, desc, data_ini.getTime(), data_fin.getTime());
                            pj.addTasca(tasca);
                        }
                    }
                    llistatProjectes.add(pj);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return llistatProjectes;
    }
}
