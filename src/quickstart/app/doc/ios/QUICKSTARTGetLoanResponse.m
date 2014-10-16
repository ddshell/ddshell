//
//  QUICKSTARTGetLoanResponse.m
//  获取借款详情.响应报文
//
//  Created by 代码生成器1.0.
//

#import "QUICKSTARTGetLoanResponse.h"


#define C_AMOUNT      @"amount"
#define C_APPLYTIME      @"applytime"
#define C_IDCARDNO      @"idcardno"
#define C_INTERESTRATE      @"interestrate"
#define C_PHONENUMBER      @"phonenumber"
#define C_REALNAME      @"realname"
#define C_STATUSTEXT      @"statustext"
#define C_TERM      @"term"


@interface QUICKSTARTGetLoanResponse ()

@end

@implementation QUICKSTARTGetLoanResponse

+(id)responseWithReponseData:(NSData*)responseData {
    QUICKSTARTGetLoanResponse* response = [[self alloc] init];
    [response fromString: [[NSString alloc] initWithData: responseData encoding: NSUTF8StringEncoding]];
    return response;
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
-(void)setApplytime:(NSString*)value {
	id objcValue;
	objcValue = value;
	self.context[C_APPLYTIME] = objcValue;
}

-(NSString*)applytime {
	NSString* value = self.context[C_APPLYTIME];
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
-(void)setInterestrate:(NSString*)value {
	id objcValue;
	objcValue = value;
	self.context[C_INTERESTRATE] = objcValue;
}

-(NSString*)interestrate {
	NSString* value = self.context[C_INTERESTRATE];
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
-(void)setStatustext:(NSString*)value {
	id objcValue;
	objcValue = value;
	self.context[C_STATUSTEXT] = objcValue;
}

-(NSString*)statustext {
	NSString* value = self.context[C_STATUSTEXT];
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


@end


