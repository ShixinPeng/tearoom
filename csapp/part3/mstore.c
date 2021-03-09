//
//  mstore.c
//  part3
//
//  Created by shixinPeng on 2021/2/19.
//  Copyright © 2021 psx. All rights reserved.
//


#include <stdio.h>

/*

 .section    __TEXT,__text,regular,pure_instructions
 .build_version macos, 10, 15
 .globl    _multstore              ## -- Begin function multstore
 .p2align    4, 0x90
 _multstore:                             ## @multstore
 .cfi_startproc
 ## %bb.0:
 pushq    %rbp
 .cfi_def_cfa_offset 16
 .cfi_offset %rbp, -16
 movq    %rsp, %rbp
 .cfi_def_cfa_register %rbp
 subq    $32, %rsp
 movq    %rdi, -8(%rbp)
 movq    %rsi, -16(%rbp)
 movq    %rdx, -24(%rbp)
 movq    -8(%rbp), %rdi
 movq    -16(%rbp), %rsi
 callq    _mult2
 movq    %rax, -32(%rbp)
 movq    -32(%rbp), %rax
 movq    -24(%rbp), %rdx
 movq    %rax, (%rdx)
 addq    $32, %rsp
 popq    %rbp
 retq
 .cfi_endproc
 ## -- End function
 
 .subsections_via_symbols
 
 */

long mult2(long,long);
void multstore(long x, long y,long *dest) {
    long t = mult2(x,y);
    *dest = t;
}
