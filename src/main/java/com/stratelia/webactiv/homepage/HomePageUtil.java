/*--- formatted by Jindent 2.1, (www.c-lab.de/~jindent) ---*/

package com.stratelia.webactiv.homepage;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;

import com.stratelia.silverpeas.silvertrace.SilverTrace;
import com.stratelia.webactiv.util.exception.FromModule;
import com.stratelia.webactiv.util.exception.SilverpeasException;

/**
 * Class declaration
 * 
 * 
 * @author
 * @version %I%, %G%
 */
public class HomePageUtil
{

    /**
     * Method declaration
     * 
     * 
     * @param exception
     * @param language
     * 
     * @return
     * 
     * @see
     */
    public static String getMessageToDisplay(Throwable exception, String language)
    {
        if (exception == null)
        {
            return "Internal error : No error in parameter's request";
        }
        Collection exceptions = SilverpeasException.getChainedExceptions((Throwable) exception);
        Iterator   it = exceptions.iterator();

        while (it.hasNext())
        {
            Throwable toDisplayException = (Throwable) it.next();

            return HomePageUtil.getMessageFromException(toDisplayException, language);
        }
        return exception.getMessage();

    }


    /**
     * Method declaration
     * 
     * 
     * @param exception
     * @param language
     * 
     * @return
     * 
     * @see
     */
    public static String getMessagesToDisplay(Throwable exception, String language)
    {

        if (exception == null)
        {
            return "Internal error : No error in parameter's request";
        }
        Collection   exceptions = SilverpeasException.getChainedExceptions((Throwable) exception);

        StringBuffer result = new StringBuffer();
        Iterator     it = exceptions.iterator();

        while (it.hasNext())
        {
            Throwable toDisplayException = (Throwable) it.next();

            if (toDisplayException instanceof java.rmi.RemoteException)
            {
                continue;
            }
            if (result.length() > 0)
            {
                result.append("<BR>");
            }
            result.append(HomePageUtil.getMessageFromException(toDisplayException, language));
        }
        return result.toString();
    }


    /**
     * Method declaration
     * 
     * 
     * @param toDisplayException
     * @param language
     * 
     * @return
     * 
     * @see
     */
    public static String getMessageFromException(Throwable toDisplayException, String language)
    {
        if (toDisplayException.getMessage() == null)
        {
            return toDisplayException.getClass().getName();
        }
        if (toDisplayException instanceof FromModule)
        {
            return ((FromModule) toDisplayException).getMessageLang(language);
        }
        else if (toDisplayException instanceof RemoteException)
        {
            return getMessageFromException(((RemoteException) toDisplayException).detail, language);
        }
        else
        {
            // on affiche le label non multilangue de l'exception
            return toDisplayException.getMessage();
        }
    }



    /**
     * Method declaration
     * 
     * 
     * @param exception
     * 
     * @return
     * 
     * @see
     */
    public static Throwable getExceptionToDisplay(Throwable exception)
    {
        Throwable toDisplayException = null;

        if (exception != null)
        {
            Collection exceptions = SilverpeasException.getChainedExceptions((Throwable) exception);
            Iterator   it = exceptions.iterator();

            while (it.hasNext())
            {
                toDisplayException = (Throwable) it.next();
            }

        }
        return toDisplayException;
    }

    /**
     * Trace the exception in SilverTrace
     * 
     * @param exception
     * @param language
     * 
     */
    public static void traceException(Throwable exception)
    {
        Throwable lastEx = getExceptionToDisplay(exception);

        if ((lastEx != null) && (lastEx.getMessage() != null) && (lastEx.getMessage().indexOf("Connection reset by peer: socket write error") >= 0) && (lastEx.getMessage().indexOf("SQL") < 0))
        {
            SilverTrace.info("util", "HomePageUtil.traceException()", "root.EX_IGNORED", "Deepest", lastEx);
            SilverTrace.info("util", "HomePageUtil.traceException()", "root.EX_IGNORED", "Highest", exception);
        }
        else
        {
            if (exception instanceof FromModule)
            {
                ((FromModule) exception).traceException();
            }
            else
            {
                Throwable parcNested;
                boolean   bFound = false;

                if (exception != null)
                {
                    Collection exceptions = SilverpeasException.getChainedExceptions((Throwable) exception);
                    Iterator   it = exceptions.iterator();

                    while (it.hasNext())
                    {
                        parcNested = (Throwable) it.next();
                        if (parcNested instanceof FromModule)
                        {
                            bFound = true;
                            ((FromModule) parcNested).traceException();
                        }
                    }
                }
                if (bFound == false)
                {
                    SilverTrace.error("util", "HomePageUtil.traceException()", "util.MSG_EXCEPTION_NOT_EMBEDED", "", exception);
                }
            }
        }
    }
}
