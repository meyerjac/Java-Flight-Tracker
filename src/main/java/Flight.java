import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Flight extends Booking{
  private int id;
  private String startLocation;
  private String endLocation;
  private String carrier;
  // private Flights flights; //should have a list of flights associated with this flight

  public Flight(String startDate, String endDate, double price, int groupsize,int userId,String startLocation,String endLocation, String carrier){
    this.startDate = startDate;
    this.endDate = endDate;
    this.price = price;
    this.groupSize = groupsize;
    this.userId = userId;
    this.startLocation = startLocation;
    this.endLocation = endLocation;
    this.carrier = carrier;
  }

  public int getId(){
    return id;
  }

  public String getStartLocation(){
    return startLocation;
  }

  public String getEndLocation(){
    return startLocation;
  }

  public static List<Flight> all(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM flights";
      return con.createQuery(sql).executeAndFetch(Flight.class);
    }
  }

  public void setUserId(int userId){
    this.userId=userId;
  }

  public void save(){
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO flights (startdate, enddate,startLocation,endLocation, price, groupsize,userid,carrier) VALUES (:startDate, :endDate, :startLocation, :endLocation,:price,:groupsize,:userid,:carrier)";
      this.id = (int) con.createQuery(sql,true)
        .addParameter("startDate",this.startDate)
        .addParameter("endDate",this.endDate)
        .addParameter("price",this.price)
        .addParameter("groupsize",this.groupSize)
        .addParameter("userid",this.userId)
        .addParameter("startLocation",this.startLocation)
        .addParameter("endLocation",this.endLocation)
        .addParameter("carrier",this.carrier)
        .executeUpdate().getKey();
    }
  }

  public static Flight find(int id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM flights WHERE id=:id";
      return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Flight.class);
    }
  }

  public void delete(){
    try(Connection con = DB.sql2o.open()){
      String sql = "DELETE FROM flights WHERE id = :id";
      con.createQuery(sql).addParameter("id",id).executeUpdate();
    }
  }

}
