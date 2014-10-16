//
//  QUICKSTARTGetPasswordByEmailRequest.m
//  邮箱找回密码.请求报文
//
//  Created by 代码生成器1.0.
//

#import "QUICKSTARTGetPasswordByEmailRequest.h"
#import "QUICKSTARTGetPasswordByEmailResponse.h"


#define C_EMAIL      @"email"


@implementation QUICKSTARTGetPasswordByEmailRequest

+(Class)responseClass {    
    return [QUICKSTARTGetPasswordByEmailResponse class];
}

+(NSString*)RA_type {
    return @"getPasswordByEmail";
}

-(id)init {
    self = [super init];
    if ( self ) {
    }
    return self;
}

-(void)setEmail:(NSString*)value {
	id objcValue;
	objcValue = value;
	self.context[C_EMAIL] = objcValue;
}

-(NSString*)email {
	NSString* value = self.context[C_EMAIL];
	return value;
}


@end


