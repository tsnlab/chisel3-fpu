package com.tsnlab.fpu_playground_01

import chisel3._
import chisel3.stage.{ChiselGeneratorAnnotation, ChiselStage}
import fudian.FADD

class IEEEFAdd(exponent: Int, mantissa: Int) extends Module {
  val io = IO(new Bundle {
    val a                = Input(UInt((exponent + mantissa).W))
    val b                = Input(UInt((exponent + mantissa).W))
    val output           = Output(UInt((exponent + mantissa).W))
  })

  val cnt = RegInit(0.U((exponent + mantissa).W)) // 32-bit clock divider
  cnt := cnt + 1.U
  val fma = Module(
    new FADD(exponent, mantissa)
  )

  fma.io.a := io.a
  fma.io.b := io.b
  fma.io.rm := 0.U
  io.output := fma.io.result
}
