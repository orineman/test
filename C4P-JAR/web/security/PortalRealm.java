


package com.infowave.c4p.web.security;



import com.infowave.c4p.util.security.PasswordEncoder;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.realm.GenericPrincipal;
import org.apache.catalina.realm.RealmBase;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.security.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Implmentation of Realm that works with any JDBC supported database.
 * This realm uses a connection pool.
 */

public class PortalRealm extends RealmBase
{
  /** The connection username to use when trying to connect to the database.*/
  protected String connectionName = null;
  /** The connection URL to use when trying to connect to the database.*/
  protected String connectionPassword = null;
  /** The connection URL to use when trying to connect to the database. */
  protected String connectionURL = null;
  /** The connection to the database.*/
  protected Connection dbConnection = null;
  /** The JDBC driver to use.*/
  protected String driverName = null;
  /** The PreparedStatement to use for identifying the roles for
   * a specified user.*/
  protected PreparedStatement preparedRoles = null;
  /** The column in the user role table that names a role*/
  protected String roleNameCol = null;
  /** Maximum number of dB connections in pool. Make sure you
   configure your mysqld max_connections large enough to handle
   all of your db connections. Set to 0 for no limit. */
  private int maxActive;
  /** Maximum number of idle dB connections to retain in pool. Set to 0 for no
   * limit */
  private int maxIdle;
  /** Maximum time to wait for a dB connection to become available
   * in ms. An Exception is thrown if
   * this timeout is exceeded.  Set to -1 to wait indefinitely.*/
  private int maxWait;
  /**  The table that holds user data.*/
  protected String userTable = null;
  /** The column in the user table that holds the user's credintials */
  protected String userCredCol = null;
  /** The column in the user table that holds the user's name */
  protected String userNameCol = null;
  /** The column that defines the type of authentication to be performed */
  protected String authTypeCol= null;

  /** Descriptive information about this Realm implementation.*/
  protected static final String info =
      "com.infowave.c4p.web.security.PortalRealm/1.0";
  /** Descriptive information about this Realm implementation.*/
  protected static final String name = "PortalRealm";

  public String getInfo()
  {
    //return "com.infowave.c4p.web.security.PortalRealm/1.0";
    return info;
  }

  public String getName()
  {
    return name;
  }



  /** The connection pool */
  private static DataSource m_datasource = null;

  public String getAuthTypeCol()
  {
    return authTypeCol;
  }

  public void setAuthTypeCol(String authTypeCol)
  {
    this.authTypeCol = authTypeCol;
  }

  public String getConnectionName()
  {
    return connectionName;
  }

  public void setConnectionName(String connectionName)
  {
    this.connectionName = connectionName;
  }

  public String getConnectionPassword()
  {
    return connectionPassword;
  }

  public void setConnectionPassword(String connectionPassword)
  {
    this.connectionPassword = connectionPassword;
  }

  public String getConnectionURL()
  {
    return connectionURL;
  }

  public void setConnectionURL(String connectionURL)
  {
    this.connectionURL = connectionURL;
  }

  public Connection getDbConnection()
  {
    return dbConnection;
  }

  public void setDbConnection(Connection dbConnection)
  {
    this.dbConnection = dbConnection;
  }

  public String getDriverName()
  {
    return driverName;
  }

  public void setDriverName(String driverName)
  {
    this.driverName = driverName;
  }

  public PreparedStatement getPreparedRoles()
  {
    return preparedRoles;
  }

  public void setPreparedRoles(PreparedStatement preparedRoles)
  {
    this.preparedRoles = preparedRoles;
  }

  public String getRoleNameCol()
  {
    return roleNameCol;
  }

  public void setRoleNameCol(String roleNameCol)
  {
    this.roleNameCol = roleNameCol;
  }

  public int getMaxActive()
  {
    return maxActive;
  }

  public void setMaxActive(int maxActive)
  {
    this.maxActive = maxActive;
  }

  public int getMaxIdle()
  {
    return maxIdle;
  }

  public void setMaxIdle(int maxIdle)
  {
    this.maxIdle = maxIdle;
  }

  public int getMaxWait()
  {
    return maxWait;
  }

  public void setMaxWait(int maxWait)
  {
    this.maxWait = maxWait;
  }

  public String getUserTable()
  {
    return userTable;
  }

  public void setUserTable(String userTable)
  {
    this.userTable = userTable;
  }

  public String getUserCredCol()
  {
    return userCredCol;
  }

  public void setUserCredCol(String userCredCol)
  {
    this.userCredCol = userCredCol;
  }

  public String getUserNameCol()
  {
    return userNameCol;
  }

  public void setUserNameCol(String userNameCol)
  {
    this.userNameCol = userNameCol;
  }




  // --------------------------------------------------------- RealmBase Methods
  /**
   * Return the Principal associated with the specified username and
   * credentials, if there is one; otherwise return <code>null</code>.
   *
   * If there are any errors with the JDBC connection, executing
   * the query or anything we return null (don't authenticate). This
   * event is also logged, and the connection will be closed so that
   * a subsequent request will automatically re-open it.
   *
   * @param username Username of the Principal to look up
   * @param credentials Password or other credentials to use in
   *  authenticating this username
   */
  public Principal authenticate(String username, String credentials)
  {
    Connection dbConnection = null;
    try {
      // Get a database connection
      dbConnection = m_datasource.getConnection();

      // Acquire a Principal object for this user
      Principal principal = authenticate(dbConnection,
                                         username, credentials);
      // Return the Principal (if any)
      return (principal);

    } catch (SQLException e) {
      // Log the problem for posterity
      //log(sm.getString("jdbcRealm.exception"), e);
      log(sm.getString("jdbcRealm.exception") + e.toString());
      // Return "not authenticated" for this request
      return (null);
    }
    finally {
      if (dbConnection != null) {
        try {dbConnection.close();} catch(Exception e){}}
    }
  }
  /**
   * Return the Principal associated with the given user name.
   */
  protected Principal getPrincipal(String username)
  {
    return (null);
  }
  /**
   * Return the password associated with the given principal's user name.
   */
  protected String getPassword(String username)
  {
    return (null);
  }
  // ------------------------------------------------------------ helper methods
  /**
   * Return the Principal associated with the specified username and
   * credentials, if there is one; otherwise return <code>null</code>.
   *
   * @param dbConnection The database connection to be used
   * @param username Username of the Principal to look up
   * @param credentials Password or other credentials to use in
   *  authenticating this username
   *
   * @exception SQLException if a database error occurs
   */
  private Principal authenticate(Connection dbConnection,
                                 String username,
                                 String credentials)
          throws SQLException
  {
    // Look up the user's credentials
    String dbCredentials = null;
    String role = null;
    String authType = null;

    PreparedStatement stmt = credentials2(dbConnection, username);
    ResultSet rs = stmt.executeQuery();
    while (rs.next()) {
      //System.out.println(rs.toString());
      //System.out.println(rs.getString(1) + " " + rs.getString(2)+ " " +rs.getString(3));
      dbCredentials = rs.getString(1).trim();
      role = rs.getString(2).trim();
      authType = rs.getString(3).trim();
    }
    //System.out.println("role: " + role + " auth: " + authType);
    rs.close();
    if (dbCredentials == null) {
      return (null);
    }

    // Find the pasword encoder for that user
    PasswordEncoder encoder = null;
    try {
      Class theClass = Class.forName(authType);
      encoder = (PasswordEncoder) theClass.newInstance();
    }
    catch (Exception e) {
      log("Cannot create the password encoder: " + authType);
      return null;
    }

    //if (debug >= 2) {
      log("->Username: " + username + "  encoded password: " +
          encoder.encode(credentials) +
          " db password: " + dbCredentials);
    //}
    // Validate the user's credentials
    boolean validated = false;
    validated = encoder.match(credentials,dbCredentials);

    if (validated) {
      //if (debug >= 2)
        log("PortalRealm success: " + username);
    } else {
      //if (debug >= 2)
        log("PortalRealm failure: " + username);
      return (null);
    }

    // Accumulate the user's roles
    ArrayList list = new ArrayList();
    list.add(role);

    // Create and return a suitable Principal for this user
    return (new GenericPrincipal(this, username, credentials, list));

  }


  /**
   * Return a PreparedStatement configured to perform the SELECT required
   * to retrieve user credentials for the specified username.
   *
   * @param dbConnection The database connection to be used
   * @param username Username for which credentials should be retrieved
   *
   * @exception SQLException if a database error occurs
   */
  private PreparedStatement credentials2(Connection dbConnection,
                                         String username)
          throws SQLException
  {
    PreparedStatement preparedCredentials;

    StringBuffer sb = new StringBuffer("SELECT ");
    sb.append(userCredCol);
    sb.append(",");
    sb.append(roleNameCol);
    sb.append(",");
    sb.append(authTypeCol);
    sb.append(" FROM ");
    sb.append(userTable);
    sb.append(" WHERE ");
    sb.append(userNameCol);
    sb.append(" = ?");

    preparedCredentials = dbConnection.prepareStatement(sb.toString());
    preparedCredentials.setString(1, username);
    return (preparedCredentials);
  }




  /**
   *
   * Prepare for active use of the public methods of this Component.
   *
   * @exception LifecycleException if this component detects a fatal error
   *  that prevents it from being started
   */
  public void start() throws LifecycleException
  {
    // Create the datasource
    BasicDataSource ds = new BasicDataSource();
    ds.setDriverClassName(getDriverName());
    ds.setUsername(getConnectionName());
    ds.setPassword(getConnectionPassword());
    ds.setUrl(getConnectionURL());
    ds.setMaxActive(getMaxActive());
    ds.setMaxIdle(getMaxIdle());
    ds.setMaxWait(getMaxWait());
    m_datasource = ds;

    // Perform normal superclass initialization
    super.start();
  }


  /**
   * Gracefully shut down active use of the public methods of this Component.
   *
   * @exception LifecycleException if this component detects a fatal error
   *  that needs to be reported
   */
  public void stop() throws LifecycleException
  {
    try {
      ((BasicDataSource)m_datasource).close();
    }
    catch(SQLException e){
      log("cannot close the datasource,e");
    }
    // Perform normal superclass finalization
    super.stop();



  }
  private void log(String msg)
 {
   System.out.println("JAE " +msg);
 }

  // ------------------------------------------------------ test Methods



// test
  public static void main(String[] args)
  {
    try {
      PortalRealm realm = new PortalRealm();
      realm.setConnectionName("vincent");
      realm.setConnectionPassword("vincent");
      realm.setConnectionURL("jdbc:mysql://localhost/proto");
      realm.setUserTable("authentication");
      realm.setUserNameCol("username");
      realm.setRoleNameCol("rolename");
      realm.setAuthTypeCol("authtype");
      realm.setUserCredCol("password");
      realm.setDriverName("com.mysql.jdbc.Driver");
      Principal principal = realm.authenticate("acc_0.0","p");
      System.out.println(principal);
    }
    catch(Exception e){e.printStackTrace();}
  }


}
