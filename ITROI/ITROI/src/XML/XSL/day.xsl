<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:day="http://www.itroi.org/day">
<xsl:import href="task.xsl"/>
    <xsl:template match="/">
        <html>
            <body>
                <xsl:value-of select="day:Day/day:DayOfWeek"/><br/>
                <xsl:value-of select="day:Day/day:date"/><br/>
                <xsl:for-each select="day:Day/day:Tasks/day:Task">
                    <xsl:call-template name ="task">
                    </xsl:call-template>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>