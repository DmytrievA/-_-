<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:day="http://www.itroi.org/day" xmlns:task="http://www.itroi.org/task"
                xmlns:user="http://www.itroi.org/user" xmlns:rem="http://www.itroi.org/reminder">

    <xsl:template match="/rem:Reminder">
        <html>
            <body>
                    <xsl:call-template name="reminder">
                    </xsl:call-template>
            </body>
        </html>
    </xsl:template>

    <xsl:template  name="reminder" >
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
                        TaskId:
                    </td>
                    <td>
                        <xsl:value-of select="rem:taskId"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        TaskName:
                    </td>
                    <td>
                        <xsl:value-of select="rem:taskName"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Date:
                    </td>
                    <td>
                        <xsl:value-of select="rem:time"/>
                    </td>
                </tr>
            </table>
    </xsl:template>

</xsl:stylesheet>