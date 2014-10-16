//
//  QUICKSTARTGetPasswordByPhoneRequest.m
//  手机找回密码.请求报文
//
//  Created by 代码生成器1.0.
//

#import "QUICKSTARTGetPasswordByPhoneRequest.h"
#import "QUICKSTARTGetPasswordByPhoneResponse.h"


#define C_PHONENUMBER      @"phonenumber"


@implementation QUICKSTARTGetPasswordByPhoneRequest

+(Class)responseClass {    
    return [QUICKSTARTGetPasswordByPhoneResponse class];
}

+(NSString*)RA_type {
    return @"getPasswordByPhone";
}

-(id)init {
    self = [super init];
    if ( self ) {
    }
    return self;
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


@end


