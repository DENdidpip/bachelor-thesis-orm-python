package vsa;

import jakarta.activation.MimetypesFileTypeMap;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.File;

/**
 *
 * @author 
 */
@Path("skola")
public class SkolaResource {
    
    // http://localhost:8080/SkolaRest/resources/skola
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getInfo() {
        return "FEI STU";
    }
    
    // http://localhost:8080/SkolaRest/resources/skola/adresa
    @GET
    @Path("adresa")
    @Produces(MediaType.TEXT_PLAIN)     // skuste zmenit na MediaType.TEXT_HTML
    public String getAdresa() {
        return "Ilkovicova 2, Bratislava";
    }
    
    // http://localhost:8080/SkolaRest/resources/skola/predmet/VSA
    @GET
    @Path("predmet/{nazov}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getPredmet(@PathParam("nazov") String name) {

        if ("VSA".equals(name)) {
            return Response
                    .status(Response.Status.OK)
                    .entity("Informacie o predmete VSA")
                    .build();
        }
        if ("TZI".equals(name)) {
            return Response
                    .status(Response.Status.OK)
                    .entity("Informacie o predmete OOP")
                    .build();
        }
// a.t.d.
        return Response
            .status(Response.Status.NOT_IMPLEMENTED)
            .build();
    }
    
//    public String getPredmet(@PathParam("nazov") String name) {
//       if ("VSA".equals(name))return "Informácie k predmetu VSA ...";
//       if ("OOP".equals(name))return "Informácie k predmetu OOP ...";
//// a.t.d.
//       return null;
//    }

    @GET
    @Produces("image/*")
    public Response getLogo() {
        File f = new File("/home/edu/Pictures/feistu.jpg");
        if (!f.exists()) {
            throw new WebApplicationException(404);
        }
        String mt = new MimetypesFileTypeMap().getContentType(f);
        return Response.ok(f, mt).build();    
    }
   
//     public Response getLogo(@Context ServletContext ctx) {
//        File f = new File(ctx.getRealPath("feistu.jpg"));
//        if (!f.exists()) {
//            throw new WebApplicationException(405);
//        }
//        String mt = new MimetypesFileTypeMap().getContentType(f);
//        return Response.ok(f, mt).build();    
//    } 
}
