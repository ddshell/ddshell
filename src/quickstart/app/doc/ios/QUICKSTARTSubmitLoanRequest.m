//
//  QUICKSTARTSubmitLoanRequest.m
//  提交借款申请.请求报文
//
//  Created by 代码生成器1.0.
//

#import "QUICKSTARTSubmitLoanRequest.h"
#import "QUICKSTARTSubmitLoanResponse.h"


#define C_AMOUNT      @"amount"
#define C_IDCARDNO      @"idcardno"
#define C_PHONENUMBER      @"phonenumber"
#define C_REALNAME      @"realname"
#define C_TERM      @"term"
#define C_USES      @"uses"


@implementation QUICKSTARTSubmitLoanRequest

+(Class)responseClass {    
    return [QUICKSTARTSubmitLoanResponse class];
}

+(NSString*)RA_type {
    return @"submitLoan";
}

-(id)init {
    self = [super init];
    if ( self ) {
    }
    return self;
}

-(void)setAmount:(NSString*)value {
	id objcValue;
	objcValue = value;
	self.context[C_AMOUNT] = objcValue;
}

-(NSString*)amount {
	NSString* value = self.context[C_AMOUNT];
	return value;
}
-(void)setIdcardno:(NSString*)value {
	id objcValue;
	objcValue = value;
	self.context[C_IDCARDNO] = objcValue;
}

-(NSString*)idcardno {
	NSString* value = self.context[C_IDCARDNO];
	return value;
}
-(void)setPhonenumber:(NSString*)value {
	id objcValue;
	objcValue = value;
	self.context[C_PHONENUMBER] = objcValue;
}

-(NSString*)phonenumber {
	NSString* value = self.context[C_PHONENUMBER];
	return value;
}
-(void)setRealname:(NSString*)value {
	id objcValue;
	objcValue = value;
	self.context[C_REALNAME] = objcValue;
}

-(NSString*)realname {
	NSString* value = self.context[C_REALNAME];
	return value;
}
-(void)setTerm:(NSString*)value {
	id objcValue;
	objcValue = value;
	self.context[C_TERM] = objcValue;
}

-(NSString*)term {
	NSString* value = self.context[C_TERM];
	return value;
}
-(void)setUses:(NSString*)value {
	id objcValue;
	objcValue = value;
	self.context[C_USES] = objcValue;
}

-(NSString*)uses {
	NSString* value = self.context[C_USES];
	return value;
}


@end


