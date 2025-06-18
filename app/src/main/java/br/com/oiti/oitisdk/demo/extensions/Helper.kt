package br.com.oiti.oitisdk.demo.extensions

import br.com.oiti.manager.exports.LivenessResult

// extension helper
fun LivenessResult.format(): String = """
    Valid: $valid
    codID: $codID
    causa: $cause
    protocolo: $protocol
    scanResultBlob: $scanResultBlob
""".trimIndent()