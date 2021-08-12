package ex10;

import ex8.QueryArgsProvider;
import ex8.User;
import ex8.WhiteSourceAppEx1;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class WhiteSourceAppEx3 {


    public static Connection dbConnection;

    HttpServletRequest req;
    public  boolean shouldFilter;

    public static User userw;
    public static List<Integer> projectIds;

    private static final String COUNT_SOURCE_FILES_QUERY_PREFIX = " SELECT COUNT(t.sourceFileId) FROM (";
    private boolean countOnly;
    private static final String EMPTY_STRING = "";
    public static QueryArgsProvider queryArgsProvider;
    private static final String SOURCE_FILE_NAME_FILTER_PATTERN = " AND sf.name REGEXP :sourceFileName";
     private static final String SOURCE_FILE_SHA1_FILTER_PATTERN = " AND sf.sha1 REGEXP :sourceFileSha1";
    private static final String SOURCE_FILE_PATH_FILTER_PATTERN = " AND psl.localPath REGEXP :sourceFilePath";
    private static final String RESOURCE_TYPE_FILTER_PATTERN = " AND mr.resolvedType REGEXP :type";
    private static final String DESCRIPTION_FILTER_PATTERN = " AND mr.description REGEXP :description";
    private static final String LOCATIONS_FILTER_PATTERN = " AND psu.localPath IS NOT NULL AND psu.localPath REGEXP :locationsFilter";
    private static final String CUSTOM_ATTRIBUTE_FILTER_PATTERN = " AND attr.value REGEXP :attributeValueFilter AND attr.definitionId = :attributeTypeFilter";
    private static final String CUSTOM_ATTRIBUTE_FILTER_PATTERN_FOR_DUE_DILIGENCE_REPORT = " AND " +
            "  ((attr.contextType = 'PROJECT' AND attr.contextId = p.id) OR " +
            "   (attr.contextType = 'PRODUCT' AND attr.contextId = prd.id) OR " +
            "   (attr.contextType = 'DOMAIN' AND attr.contextId = prd.domainId))";
    private static final String SOURCE_FILE_NAME = "sourceFileName";
    private static final String LIBRARY = "library";
    private static final String LIBRARY_FILTER_PATTERN = " AND mr.displayName REGEXP :libraryFilter";
    public static boolean tmp;




    void f(WhiteSourceAppEx1.ContextType context) throws SQLException {
              String queryBuilder = "SELECT ....";

             if (shouldFilter) {
                 WhiteSourceAppEx1.ContextType filterType1 = queryArgsProvider.getFilterType();
                 switch (filterType1) {
                     case SYSTEM:
                         queryBuilder = queryBuilder + SOURCE_FILE_NAME_FILTER_PATTERN;
                         queryBuilder = queryBuilder + req.getParameter("one");
                        break;

                    default:
                        queryBuilder = queryBuilder + req.getParameter("2");
                        queryBuilder = queryBuilder + req.getParameter("3");
                        break;

                }
            }

        queryBuilder = queryBuilder + "sdfsdf";

        PreparedStatement sqlStatement = dbConnection.prepareStatement( queryBuilder);

        sqlStatement.execute();
    }


    private String  createSelectQuery(  boolean filterInHouse, String queryBuilder ) {
        String format;
        String result = queryBuilder;
        if (filterInHouse) {
            format = "SAFE";
        }else{
            format =   req.getParameter("one");
        }
        result = result + format;

        return result;
    }

}
