#!/usr/bin/env groovy

def call(String reportFileUrl = null) {
  //echo "Hello, ${name}."

  int highs = 0
  int mediums = 0
  int lows = 0
  int infos = 0

try {
  def xmlfile = new File(reportFileUrl);
  def doc = new XmlSlurper(false, false, true).parse(xmlfile)
  echo 'Start transforming XML'

  //Get Scan Details (Results, Languages, Queries)
  doc.Query.each { query ->
    println "query index: ${query.@QueryPath}"
    query.Result.each { result ->
      def severityVal = ${result.@Severity}
      switch(severityVal) {
        case "High":
          highs++
          break
        case "Medium"
          mediums++
          break
        case "Low"
          lows++
          break
        case "Info"
          infos++
          break

        default:
      }
    }

  }

} catch(Exception ex) {
  println(ex.toString());
  println(ex.getMessage());
  println(ex.getStackTrace());
}
  [highs: highs, mediums: mediums, lows: lows, infos:infos]

}
