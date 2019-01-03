#!/usr/bin/env groovy

def call(String reportFileUrl = null) {
  //echo "Hello, ${name}."

  int highs = 0
  int mediums = 0
  int lows = 0
  int infos = 0


  def xmlfile = new File(reportFileUrl);
  def doc = new XmlSlurper(false, false, true).parse(xmlfile)
  echo 'Start transforming XML'


  //Get Scan Details (Results, Languages, Queries)
  doc.Query.each { query ->
    //println "query index: ${query.@QueryPath}"
    query.Result.each { result ->
      //println "severity index: ${result.@Severity}"
      def severity = "${result.@Severity}"

      switch(severity) {
        case "High":
          highs++
          break
        case "Medium":
          mediums++
          break
        case "Low":
          lows++
          break
        case "Information":
          infos++
          break
      }
    }

  }

  return [highs: highs, mediums: mediums, lows: lows, infos:infos]

}
