//
//  QUICKSTARTSubmitLoanRequest.h
//  提交借款申请.请求报文
//
//  Created by 代码生成器1.0.
//
#import "MessageRequest.h"

@interface QUICKSTARTSubmitLoanRequest : MessageRequest
-(void)setAmount:(NSString*)value;
-(NSString*)amount;
-(void)setIdcardno:(NSString*)value;
-(NSString*)idcardno;
-(void)setPhonenumber:(NSString*)value;
-(NSString*)phonenumber;
-(void)setRealname:(NSString*)value;
-(NSString*)realname;
-(void)setTerm:(NSString*)value;
-(NSString*)term;
-(void)setUses:(NSString*)value;
-(NSString*)uses;

@end


