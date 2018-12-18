<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:tasks="Tasks.xsd">
<xsl:import href="task.xsl"/>
    <xsl:template match="tasks:tasks">
        <html>
            <body>
                <xsl:for-each select="tasks:task">
                    <xsl:call-template name ="task">
                    </xsl:call-template>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>