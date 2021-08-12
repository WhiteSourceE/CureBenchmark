package ex8;

import org.apache.commons.lang.StringUtils;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WhiteSourceAppEx1 {


    public static Connection dbConnection;

    public enum ContextType {

        SYSTEM,
        GLOBAL_ACCOUNT,
        DOMAIN,
        PRODUCT,
        PROJECT,

        UNSUPPORTED_TYPE;

        private static ArrayList<ContextType> supportedTypes;

        static {
            supportedTypes = new ArrayList<>();
            supportedTypes.add(DOMAIN);
            supportedTypes.add(PRODUCT);
            supportedTypes.add(PROJECT);
            supportedTypes.add(GLOBAL_ACCOUNT);
            supportedTypes.add(SYSTEM);
        }

        public static ArrayList<ContextType> getSupportedTypes() {
            return supportedTypes;
        }

    }
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




    void f(ContextType context) throws SQLException {
              StringBuilder queryBuilder = new StringBuilder();
            if (countOnly) {
                queryBuilder.append(COUNT_SOURCE_FILES_QUERY_PREFIX);
            }

            String filterInHouseQuery = EMPTY_STRING;
            String projectIdsAsString = String.valueOf(projectIds);

            createSelectQuery(  true,queryBuilder);

            String filterValue = queryArgsProvider.getFilterValue();

            String filterType = EMPTY_STRING;

             if (shouldFilter) {
                 ContextType filterType1 = queryArgsProvider.getFilterType();
                 switch (filterType1) {
                     case SYSTEM:
                        queryBuilder.append(SOURCE_FILE_NAME_FILTER_PATTERN);
                        break;
                    case GLOBAL_ACCOUNT:
                        queryBuilder.append(LIBRARY_FILTER_PATTERN);
                        break;

                    default: queryBuilder.append(req.getParameter("two"));;
                }
            }

            // filter by source file origin match type
            if (tmp) {
                 if (queryArgsProvider != null) {
                    if (tmp) {
                        queryBuilder.append(queryBuilder.append(req.getParameter("3")));
                    } else {
                        queryBuilder.append("SAFE");
                    }
                }
            }

        PreparedStatement sqlStatement = dbConnection.prepareStatement( queryBuilder.toString());

        sqlStatement.execute();

    }


    private void  createSelectQuery(  boolean filterInHouse, StringBuilder queryBuilder ) {
        String format;
        if (filterInHouse) {
            format = "SAFE";
        }else{
            format =   req.getParameter("one");
        }
        queryBuilder.append(format);
    }

}
