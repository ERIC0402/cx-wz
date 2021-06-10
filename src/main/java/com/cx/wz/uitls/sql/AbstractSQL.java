package com.cx.wz.uitls.sql;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSQL<T> {

    private static final String AND = ") \nAND (";
    private static final String OR = ") \nOR (";

    private AbstractSQL.SQLStatement sql = new AbstractSQL.SQLStatement();

    public abstract T getSelf();

    public T UPDATE(String table) {
        sql().statementType = AbstractSQL.SQLStatement.StatementType.UPDATE;
        sql().tables.add(table);
        return getSelf();
    }

    public T SET(String sets) {
        sql().sets.add(sets);
        return getSelf();
    }

    public T SET_IF(String sets, boolean condition) {
        return condition ? SET(sets) : getSelf();
    }

    public T SET_IF(String sets, String otherwise, boolean condition) {
        return condition ? SET(sets) : SET(otherwise);
    }

    public T INSERT_INTO(String tableName) {
        sql().statementType = AbstractSQL.SQLStatement.StatementType.INSERT;
        sql().tables.add(tableName);
        return getSelf();
    }

    public T VALUES(String columns, String values) {
        sql().columns.add(columns);
        sql().values.add(values);
        return getSelf();
    }

    public T VALUES_IF(String columns, String values, boolean condition) {
        return condition ? VALUES(columns, values) : getSelf();
    }

    public T VALUES_IF(String columns, String values, String otherwise, boolean condition) {
        return condition ? VALUES(columns, values) : VALUES(columns, otherwise);
    }

    public T WHERE_IF(String conditions, boolean condition) {
        return condition ? WHERE(conditions) : getSelf();
    }

    public T SELECT(String columns) {
        sql().statementType = AbstractSQL.SQLStatement.StatementType.SELECT;
        sql().select.add(columns);
        return getSelf();
    }

    public T SELECT_DISTINCT(String columns) {
        sql().distinct = true;
        SELECT(columns);
        return getSelf();
    }

    public T DELETE_FROM(String table) {
        sql().statementType = AbstractSQL.SQLStatement.StatementType.DELETE;
        sql().tables.add(table);
        return getSelf();
    }

    public T FROM(String table) {
        sql().tables.add(table);
        return getSelf();
    }

    public T JOIN(String join) {
        sql().join.add(join);
        return getSelf();
    }

    public T INNER_JOIN(String join) {
        sql().innerJoin.add(join);
        return getSelf();
    }

    public T LEFT_OUTER_JOIN(String join) {
        sql().leftOuterJoin.add(join);
        return getSelf();
    }

    public T RIGHT_OUTER_JOIN(String join) {
        sql().rightOuterJoin.add(join);
        return getSelf();
    }

    public T OUTER_JOIN(String join) {
        sql().outerJoin.add(join);
        return getSelf();
    }

    public T WHERE(String conditions) {
        sql().where.add(conditions);
        sql().lastList = sql().where;
        return getSelf();
    }

    public T OR() {
        sql().lastList.add(OR);
        return getSelf();
    }

    public T AND() {
        sql().lastList.add(AND);
        return getSelf();
    }

    public T GROUP_BY(String columns) {
        sql().groupBy.add(columns);
        return getSelf();
    }

    public T HAVING(String conditions) {
        sql().having.add(conditions);
        sql().lastList = sql().having;
        return getSelf();
    }

    public T ORDER_BY(String columns) {
        sql().orderBy.add(columns);
        return getSelf();
    }

    private AbstractSQL.SQLStatement sql() {
        return sql;
    }

    public <A extends Appendable> A usingAppender(A a) {
        sql().sql(a);
        return a;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sql().sql(sb);
        return sb.toString();
    }

    private static class SafeAppendable {
        private final Appendable a;
        private boolean empty = true;

        public SafeAppendable(Appendable a) {
            super();
            this.a = a;
        }

        public AbstractSQL.SafeAppendable append(CharSequence s) {
            try {
                if (empty && s.length() > 0) {
                    empty = false;
                }
                a.append(s);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return this;
        }

        public boolean isEmpty() {
            return empty;
        }

    }

    private static class SQLStatement {

        public enum StatementType {
            DELETE, INSERT, SELECT, UPDATE
        }

        AbstractSQL.SQLStatement.StatementType statementType;
        List<String> sets = new ArrayList<String>();
        List<String> select = new ArrayList<String>();
        List<String> tables = new ArrayList<String>();
        List<String> join = new ArrayList<String>();
        List<String> innerJoin = new ArrayList<String>();
        List<String> outerJoin = new ArrayList<String>();
        List<String> leftOuterJoin = new ArrayList<String>();
        List<String> rightOuterJoin = new ArrayList<String>();
        List<String> where = new ArrayList<String>();
        List<String> having = new ArrayList<String>();
        List<String> groupBy = new ArrayList<String>();
        List<String> orderBy = new ArrayList<String>();
        List<String> lastList = new ArrayList<String>();
        List<String> columns = new ArrayList<String>();
        List<String> values = new ArrayList<String>();
        boolean distinct;

        public SQLStatement() {
            // Prevent Synthetic Access
        }

        private void sqlClause(AbstractSQL.SafeAppendable builder, String keyword, List<String> parts, String open, String close,
                               String conjunction) {
            if (!parts.isEmpty()) {
                if (!builder.isEmpty()) {
                    builder.append("\n");
                }
                builder.append(keyword);
                builder.append(" ");
                builder.append(open);
                String last = "________";
                for (int i = 0, n = parts.size(); i < n; i++) {
                    String part = parts.get(i);
                    if (i > 0 && !part.equals(AND) && !part.equals(OR) && !last.equals(AND) && !last.equals(OR)) {
                        builder.append(conjunction);
                    }
                    builder.append(part);
                    last = part;
                }
                builder.append(close);
            }
        }

        private String selectSQL(AbstractSQL.SafeAppendable builder) {
            if (distinct) {
                sqlClause(builder, "SELECT DISTINCT", select, "", "", ", ");
            } else {
                sqlClause(builder, "SELECT", select, "", "", ", ");
            }

            sqlClause(builder, "FROM", tables, "", "", ", ");
            sqlClause(builder, "JOIN", join, "", "", "\nJOIN ");
            sqlClause(builder, "INNER JOIN", innerJoin, "", "", "\nINNER JOIN ");
            sqlClause(builder, "OUTER JOIN", outerJoin, "", "", "\nOUTER JOIN ");
            sqlClause(builder, "LEFT OUTER JOIN", leftOuterJoin, "", "", "\nLEFT OUTER JOIN ");
            sqlClause(builder, "RIGHT OUTER JOIN", rightOuterJoin, "", "", "\nRIGHT OUTER JOIN ");
            sqlClause(builder, "WHERE", where, "(", ")", " AND ");
            sqlClause(builder, "GROUP BY", groupBy, "", "", ", ");
            sqlClause(builder, "HAVING", having, "(", ")", " AND ");
            sqlClause(builder, "ORDER BY", orderBy, "", "", ", ");
            return builder.toString();
        }

        private String insertSQL(AbstractSQL.SafeAppendable builder) {
            sqlClause(builder, "INSERT INTO", tables, "", "", "");
            sqlClause(builder, "", columns, "(", ")", ", ");
            sqlClause(builder, "VALUES", values, "(", ")", ", ");
            return builder.toString();
        }

        private String deleteSQL(AbstractSQL.SafeAppendable builder) {
            sqlClause(builder, "DELETE FROM", tables, "", "", "");
            sqlClause(builder, "WHERE", where, "(", ")", " AND ");
            return builder.toString();
        }

        private String updateSQL(AbstractSQL.SafeAppendable builder) {

            sqlClause(builder, "UPDATE", tables, "", "", "");
            sqlClause(builder, "SET", sets, "", "", ", ");
            sqlClause(builder, "WHERE", where, "(", ")", " AND ");
            return builder.toString();
        }

        public String sql(Appendable a) {
            AbstractSQL.SafeAppendable builder = new AbstractSQL.SafeAppendable(a);
            if (statementType == null) {
                return null;
            }

            String answer;

            switch (statementType) {
                case DELETE:
                    answer = deleteSQL(builder);
                    break;

                case INSERT:
                    answer = insertSQL(builder);
                    break;

                case SELECT:
                    answer = selectSQL(builder);
                    break;

                case UPDATE:
                    answer = updateSQL(builder);
                    break;

                default:
                    answer = null;
            }

            return answer;
        }
    }
}
