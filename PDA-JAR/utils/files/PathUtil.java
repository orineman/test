package com.infowave.pda.utils.files;

/**
 * Created by IntelliJ IDEA.
 * User: vlanik
 * Date: 11.10.2007
 * Time: 17:10:03
 * To change this template use File | Settings | File Templates.
 */
public class PathUtil
{
    public static String getParentPath ( final String path )
    {
        int idxDelim = path.lastIndexOf('\\');
        if ( idxDelim > 0 )
            return path.substring(0, idxDelim );
        return "";
    }

    public static String getParentFavoritePath ( final String path )
    {
        int idxDel = path.lastIndexOf( "\\*" );
        String UsedPath = path;

        if ( idxDel == path.length() - 2 )
            UsedPath = path.substring( 0, idxDel );

        int idxDelim = UsedPath.lastIndexOf('\\');
        if ( idxDelim > 0 )
            UsedPath = UsedPath.substring(0, idxDelim );
        else
            return "";

        return UsedPath += "\\*";
    }

    public static String getFavoritePath ( final String path )
    {
        int idxDel = path.lastIndexOf( "\\*" );
        if ( idxDel == path.length() - 2 )
            return path.substring( 0, idxDel );
        
        return path;
    }
}
