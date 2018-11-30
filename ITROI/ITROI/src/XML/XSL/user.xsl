<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:day="http://www.itroi.org/day" xmlns:task="http://www.itroi.org/task"
                xmlns:user="http://www.itroi.org/user" xmlns:rem="http://www.itroi.org/reminder"
                xmlns:xsi="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/user:user">
        <html>
            <body>
                <xsi:call-template name="user">
                    <xsl:with-param name="user" select="/user:user"/>
                </xsi:call-template>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="user" name="user" >
    <xsl:param name="user"/>
            <table border="2px">
                <tr>
                    <td>
                        Id:
                    </td>
                    <td>
                        <xsl:value-of select="$user/user:name/@id"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Role:
                    </td>
                    <td>
                        <xsl:value-of select="$user/user:name/@role"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Name:
                    </td>
                    <td>
                        <xsl:value-of select="$user/user:name"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Date:
                    </td>
                    <td>
                        <xsl:value-of select="$user/user:surname"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Time:
                    </td>
                    <td>
                        <xsl:value-of select="$user/user:login"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Duration:
                    </td>
                    <td>
                        <xsl:value-of select="$user/user:email"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Desc:
                    </td>
                    <td>
                        <xsl:value-of select="$user/user:password"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Status:
                    </td>
                    <td>
                        <xsl:value-of select="$user/user:gender"/>
                    </td>
                </tr>
            </table>
    </xsl:template>

</xsl:stylesheet>