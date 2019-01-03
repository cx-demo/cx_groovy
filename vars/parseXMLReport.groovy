#!/usr/bin/env groovy

def call(String txt = null) {
  //echo "Hello, ${name}."

  int high = 0
  int mediums = 0
  int lows = 0
  int infos = 0

  def xmlNode = new XmlParser(false, false).parse(txt)
  echo 'Start transforming XML'

  //Get Scan Details (Results, Languages, Queries)
  xmlNode.'Query'.each { query ->
    query.'Result'.each { result ->
      severityVal = result.@Severity
      switch(severityVal) {
        case "High":
          high++
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
  [high: high, mediums: mediums, low: low, infos:infos]
}
