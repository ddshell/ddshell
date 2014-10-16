//
//  QUICKSTARTLoginRequest.m
//  用户登录.请求报文
//
//  Created by 代码生成器1.0.
//

#import "QUICKSTARTLoginRequest.h"
#import "QUICKSTARTLoginResponse.h"


#define C_IDCARDNO      @"idcardno"
#define C_PASSWORD      @"password"


@implementation QUICKSTARTLoginRequest

+(Class)responseClass {    
    return [QUICKSTARTLoginResponse class];
}

+(NSString*)RA_type {
    return @"login";
}

-(id)init {
    self = [super init];
    if ( self ) {
    }
    return self;
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
-(void)setPassword:(NSString*)value {
	id objcValue;
	objcValue = value;
	self.context[C_PASSWORD] = objcValue;
}

-(NSString*)password {
	NSString* value = self.context[C_PASSWORD];
	return value;
}


@end


