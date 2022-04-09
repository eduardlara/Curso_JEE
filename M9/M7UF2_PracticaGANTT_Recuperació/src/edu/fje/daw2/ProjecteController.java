package edu.fje.daw2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ProjecteController extends HttpServlet {
    private ArrayList<Projecte> llistatProjectes;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessio = request.getSession();

        String valueButton = request.getParameter("envia");

        if(valueButton.equals("Visualitza")){
            if (sessio.getAttribute("llistatProjectes")==null) {
                llistatProjectes = new ArrayList<>();
            }else {
                llistatProjectes = (ArrayList<Projecte>) sessio.getAttribute("llistatProjectes");
            }

            ArrayList<Projecte> llistatProjectes = procesaXML("gantt.xml");

            sessio.setAttribute("llistatProjectes", llistatProjectes);

            RequestDispatcher rd = request.getRequestDispatcher("vistaProjectes.jsp");
            rd.forward(request, response);
        }else if(valueButton.equals("Edita")){
            ArrayList<Projecte> llistatProjectes = procesaXML("gantt.xml");

            sessio.setAttribute("llistatProjectes", llistatProjectes);

            RequestDispatcher rd = request.getRequestDispatcher("editaProjectes.jsp");
            rd.forward(request, response);
        }else{
            ArrayList<Projecte> llistatProjectes = null;
            try {
                llistatProjectes = procesaFORM(request, response);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            sessio.setAttribute("llistatProjectes", llistatProjectes);

            RequestDispatcher rd = request.getRequestDispatcher("vistaProjectes.jsp");
            rd.forward(request, response);
        }
    }

    private ArrayList<Projecte> procesaFORM(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String[] Name = request.getParameterValues("nameP");
        String[] Desc = request.getParameterValues("descP");
        String[] DataI = request.getParameterValues("data_iniP");
        String[] DataF = request.getParameterValues("data_finP");

        ArrayList<Projecte> llistatProjectes = new ArrayList<Projecte>();
        for(int s=0; s<Name.length ; s++) {
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"/*"EEE MMM dd HH:mm:ss zzz yyyy"*/);
            Date d = format.parse(DataI[s]);
            Date dd = format.parse(DataF[s]);
            //Date d = new Date(new Long(DataI[s]));
            //Date dd = new Date(new Long(DataF[s]));

            Projecte p = new Projecte(Name[s], 0, new Integer(Desc[s]), d, dd);

            String[] NameT = request.getParameterValues("name" + Name[s]);
            String[] DescT = request.getParameterValues("desc" + Name[s]);
            String[] DataIT = request.getParameterValues("data_ini" + Name[s]);
            String[] DataFT = request.getParameterValues("data_fin" + Name[s]);

            for (int r=0 ; r<NameT.length ; r++) {
                Date f = format.parse(DataIT[r]);
                Date ff = format.parse(DataFT[r]);
                //Date f = new Date(new Long(DataIT[r]));
                //Date ff = new Date(new Long(DataFT[r]));

                Tasca t = new Tasca(NameT[r], new Integer(DescT[r]), f, ff);
                p.addTasca(t);
            }
            llistatProjectes.add(p);
        }
        return llistatProjectes;
    }

    private ArrayList<Projecte> procesaXML(String file) {
        ArrayList<Projecte> llistatProjectes = new ArrayList<Projecte>();
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
                    Projecte pj = new Projecte(name, codi, desc, data_ini.getTime(), data_fin.getTime());
                    NodeList llistaTasques = projecteElement.getElementsByTagName("tasca");

                    for(int t=0; t<llistaTasques.getLength() ; t++){
                        Node tarea = llistaTasques.item(t);
                        if(tarea.getNodeType() == Node.ELEMENT_NODE){
                            Element e = (Element)tarea;
                            name = e.getAttribute("name");
                            data_fin = format.parse(e.getAttribute("data_fin"));
                            data_ini = format.parse(e.getAttribute("data_ini"));
                            desc = Integer.parseInt(e.getAttribute("desc"));
                            Tasca tasca = new Tasca(name, desc, data_ini.getTime(), data_fin.getTime());
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