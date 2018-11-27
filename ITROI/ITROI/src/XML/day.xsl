<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:day="http://www.itroi.org/day" xmlns:task="http://www.itroi.org/task"
                xmlns:user="http://www.itroi.org/user" xmlns:rem="http://www.itroi.org/reminder">

    <xsl:template match="/day:Day">
        <html>
            <body>
                <xsl:value-of select="day:DayOfWeek"/><br/>
                <xsl:value-of select="day:date"/><br/>
                <xsl:for-each select="day:Tasks">
                    <xsl:apply-templates select ="day:Task">
                        <xsl:sort select="task:time"/>
                    </xsl:apply-templates>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="/day:Day/day:Tasks/day:Task">
        <html>
            <table border="2px">
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
            </table>
        </html>
    </xsl:template>

</xsl:stylesheet>