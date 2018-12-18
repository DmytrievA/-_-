<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:day="http://www.itroi.org/day" xmlns:task="http://www.itroi.org/task"
                xmlns:user="http://www.itroi.org/user" xmlns:rem="http://www.itroi.org/reminder">
<xsl:import href="reminders.xsl"/>
    <xsl:import href="user.xsl"/>
    <xsl:template match="task:Task">
        <html>
            <body>
                <xsl:call-template name="task">
                </xsl:call-template>
            </body>
        </html>

    </xsl:template>

    <xsl:template name="task" >

            <table border="2px">
                <tr>
                    <td>
                        Id:
                    </td>
                    <td>
                        <xsl:value-of select="@id"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Name:
                    </td>
                    <td>
                        <xsl:value-of select="task:title"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Date:
                    </td>
                    <td>
                        <xsl:value-of select="task:date"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        DayOfWeek:
                    </td>
                    <td>
                        <xsl:value-of select="task:DayOfWeek"/>
                    </td>
                </tr>
                <tr>

                    <td>
                        Time:
                    </td>
                    <td>
                        <xsl:value-of select="task:time"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Duration:
                    </td>
                    <td>
                        <xsl:value-of select="task:duration"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Desc:
                    </td>
                    <td>
                        <xsl:value-of select="task:description"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        StatusId:
                    </td>
                    <td>
                        <xsl:value-of select="task:status/@id"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Status:
                    </td>
                    <td>
                        <xsl:value-of select="task:status"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        UserId:
                    </td>
                    <td>

                            <xsl:value-of select="task:user/@id"/>

                    </td>
                </tr>
                <tr>
                    <td>
                        User:
                    </td>
                    <td>
                        <xsl:for-each select="task:user/task:userValue">
                        <xsl:call-template name ="user">
                        </xsl:call-template>
                        </xsl:for-each>
                    </td>
                </tr>

                <tr>
                    <td>
                       Reminders:
                    </td>
                    <td>
                        <xsl:for-each select="task:reminders/task:reminder">
                            <xsl:call-template name="reminder">
                            </xsl:call-template>
                        </xsl:for-each>
                    </td>
                </tr>

            </table>
<br/>
        <br/>
        <br/>
    </xsl:template>

</xsl:stylesheet>