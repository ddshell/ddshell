//
//  QUICKSTARTGetRegisterVfcodeRequest.m
//  获取注册验证码.请求报文
//
//  Created by 代码生成器1.0.
//

#import "QUICKSTARTGetRegisterVfcodeRequest.h"
#import "QUICKSTARTGetRegisterVfcodeResponse.h"


#define C_PHONENUMBER      @"phonenumber"


@implementation QUICKSTARTGetRegisterVfcodeRequest

+(Class)responseClass {    
    return [QUICKSTARTGetRegisterVfcodeResponse class];
}

+(NSString*)RA_type {
    return @"getRegisterVfcode";
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


