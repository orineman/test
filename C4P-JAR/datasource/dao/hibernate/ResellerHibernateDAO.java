package com.infowave.c4p.datasource.dao.hibernate;

import com.infowave.c4p.datasource.DataException;
import com.infowave.c4p.datasource.dao.DAOFactory;
import com.infowave.c4p.datasource.dao.ResellerDAO;
import com.infowave.c4p.model.Reseller;

import java.util.List;

// Referenced classes of package com.infowave.c4p.datasource.dao.hibernate:
//            AbstractDAO

public class ResellerHibernateDAO extends AbstractDAO
    implements ResellerDAO
{

    public ResellerHibernateDAO()
    {
    }

    public Reseller getByCode(String code)
        throws DataException
    {
        Reseller reseller = null;
        reseller = (Reseller)super.getSingleInstance("Reseller.by.code", "code", code);
        return reseller;
    }

    public void update(Reseller reseller)
        throws DataException
    {
        super.save(reseller);
    }

    public Reseller[] getAll()
        throws DataException
    {
        Reseller resellers[] = null;
        List results = super.execute("Reseller.all");
        resellers = new Reseller[results.size()];
        for(int i = 0; i < results.size(); i++)
            resellers[i] = (Reseller)results.get(i);

        return resellers;
    }

    public static void testGetAll()
        throws Exception
    {
        ResellerDAO dao = DAOFactory.getResellerDAO();
        Reseller resellers[] = dao.getAll();
        for(int i = 0; i < resellers.length; i++)
            System.out.println(resellers[i]);

    }

    public static void testGetByCode()
        throws Exception
    {
        ResellerDAO dao = DAOFactory.getResellerDAO();
        Reseller reseller = dao.getByCode("res_0");
        System.out.println(reseller);
    }

    public static void main(String args[])
    {
        try
        {
            testGetAll();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private static final String GET_BY_CODE_QUERY = "Reseller.by.code";
    private static final String GET_ALL_QUERY = "Reseller.all";
    private static final String CODE_ATTRIBUTE = "code";
}