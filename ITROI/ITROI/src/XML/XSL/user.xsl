<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"

                xmlns:user="http://www.itroi.org/user" xmlns:group="http://www.itroi.org/group"
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

    <xsl:template  name="user" >

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
                        Role:
                    </td>
                    <td>
                        <xsl:value-of select="@role"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Name:
                    </td>
                    <td>
                        <xsl:value-of select="user:name"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Surname:
                    </td>
                    <td>
                        <xsl:value-of select="user:surname"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Login:
                    </td>
                    <td>
                        <xsl:value-of select="user:login"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Email:
                    </td>
                    <td>
                        <xsl:value-of select="user:email"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Password:
                    </td>
                    <td>
                        <xsl:value-of select="user:password"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Gender:
                    </td>
                    <td>
                        <xsl:value-of select="user:gender"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        MemberOfGroups:
                    </td>
                    <td>
                        <xsl:for-each select="user:memberOfGroups/user:memberOfGroup">
                        <xsl:call-template name="memberOfGroups"/>
                        </xsl:for-each>
                    </td>
                </tr>
            </table>
    </xsl:template>
    <xsl:template  name="memberOfGroups" >

        <table border="2px">
            <tr>
                <td>
                    Id:
                </td>
                <td>
                    <xsl:value-of select="user:group/@id"/>
                </td>
            </tr>
            <tr>
                <td>
                    NameOfGroup:
                </td>
                <td>
                    <xsl:value-of select="user:group/group:name"/>
                </td>
            </tr>
            <tr>
                <td>
                    GroupRoleId:
                </td>
                <td>
                    <xsl:value-of select="user:groupRole/@id"/>
                </td>
            </tr>
            <tr>
                <td>
                    GroupRoleName:
                </td>
                <td>
                    <xsl:value-of select="user:groupRole/user:groupRoleName"/>
                </td>
            </tr>

        </table>
    </xsl:template>

</xsl:stylesheet>