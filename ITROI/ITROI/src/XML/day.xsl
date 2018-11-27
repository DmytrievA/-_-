<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:day="http://www.itroi.org/day" xmlns:task="http://www.itroi.org/task"
                xmlns:user="http://www.itroi.org/user" xmlns:rem="http://www.itroi.org/reminder">

    <xsl:template match="/day:Day">
        <html>
            <body>
                <xsl:value-of select="day:DayOfWeek"/><br/>
                <xsl:value-of select="day:date"/><br/>
                <xsl:for-each select="day:Tasks/day:Task">
                    <hr/>
                    <strong><xsl:value-of select="task:title"/></strong><br/>
                    <xsl:value-of select="task:date"/><br/>
                    <xsl:value-of select="task:time"/><br/>
                    <xsl:value-of select="task:duration"/><br/>
                    <xsl:value-of select="task:description"/><br/>
                    <p>Истекает в: </p>
                    <xsl:value-of select="task:status/task:expired"/><br/>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="/day:Tasks">
        <html>
            <xsl:for-each select="day:Task">
                <xsl:value-of select="task:title"/><br/>
                <xsl:value-of select="task:date"/><br/>
                <xsl:value-of select="task:time"/><br/>
                <xsl:value-of select="task:duration"/><br/>
                <xsl:value-of select="task:description"/><br/>
            </xsl:for-each><br/>
        </html>
    </xsl:template>

</xsl:stylesheet>