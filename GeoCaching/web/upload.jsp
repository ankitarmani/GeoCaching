<%@page import="java.util.ArrayList"%>
<%@page import="java.security.MessageDigest"%>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.io.File" %>
<%@page import="java.util.UUID" %>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.*"%>

<%
    ServletContext context = pageContext.getServletContext();
    String folder = context.getInitParameter("file-upload");// this is the way to access web.xml
    //String folder = context.getRealPath(File.separator).replaceAll("build"+File.separator, "") +"uploads"+File.separator;

    FileItemFactory factory = new DiskFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload(factory);
    List items = null;
    
    try
    {
        items = upload.parseRequest(request);
    }
    catch (FileUploadException e)
    {
        e.printStackTrace();
    }
    
        
    List<String> souvenirsLocations = new ArrayList<String>();
    List<Integer> downloads = new ArrayList<Integer>();
    
    Iterator itr = items.iterator();
    
    session.setAttribute("souvenirsDownloads", null);
    session.setAttribute("souvenirsLocations", null);  
    session.setAttribute("bageLocation", null);


    while (itr.hasNext())
    {
        FileItem item = (FileItem) itr.next();
        if (item.isFormField())
        {
            if (item.getFieldName().equals("souvenirNumber"))
            {
                for (int i=1; i<=Integer.parseInt(item.getString()); i++)
                {
                    downloads.add(0);
                    souvenirsLocations.add(null);
                }
            }
        }
    }
    
    itr = items.iterator();
    while (itr.hasNext())
    {
        FileItem item = (FileItem) itr.next();
        
        if (item.getSize()==0) continue;
        if (item.isFormField())
        {
            if (item.getFieldName().startsWith("download"))
            {
                String name = item.getFieldName();
                int index = Integer.parseInt(name.substring(name.length()-1, name.length()));
                if (index-1<downloads.size())
                {
                    downloads.set(index-1, Integer.parseInt(item.getString()));
                }
            }
        }
        else
        {
            try
            {
                out.print(folder);
               // MessageDigest md = MessageDigest.getInstance("MD5");
               // byte[] thedigest = md.digest(item.get());
                
                UUID uuid = UUID.randomUUID();
                String randomUUIDString = uuid.toString();
                
                String savedFileLocation;
                
                boolean flagOld;
                
                if (!item.getName().endsWith("inDB"))
                {
                    savedFileLocation = folder+randomUUIDString+"inDB";
                    flagOld = false;
                }
                else
                {
                    savedFileLocation = folder+item.getName();  
                    flagOld = true;
                }
                //String savedFileLocation = folder+new String(thedigest)+item.getName();
                if (item.getFieldName().equals("bage"))
                {
                    session.setAttribute("bageLocation", savedFileLocation);
                }
                else if (item.getFieldName().startsWith("souvenirs"))
                {
                    String name = item.getFieldName();
                    int index = Integer.parseInt(name.substring(name.length()-1, name.length()));
                    souvenirsLocations.set(index-1, savedFileLocation);
                }
                if (!flagOld)
                {
                    File savedFile = new File(savedFileLocation);
                    item.write(savedFile); 
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    session.setAttribute("souvenirsDownloads", downloads);

    session.setAttribute("souvenirsLocations", souvenirsLocations);  

%>