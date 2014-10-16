//
//  QUICKSTARTRegisterRequest.m
//  用户注册.请求报文
//
//  Created by 代码生成器1.0.
//

#import "QUICKSTARTRegisterRequest.h"
#import "QUICKSTARTRegisterResponse.h"


#define C_EMAIL      @"email"
#define C_IDCARDNO      @"idcardno"
#define C_PASSWORD      @"password"
#define C_PHONENUMBER      @"phonenumber"
#define C_REALNAME      @"realname"
#define C_VFCODE      @"vfcode"


@implementation QUICKSTARTRegisterRequest

+(Class)responseClass {    
    return [QUICKSTARTRegisterResponse class];
}

+(NSString*)RA_type {
    return @"register";
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
-(void)setVfcode:(NSString*)value {
	id objcValue;
	objcValue = value;
	self.context[C_VFCODE] = objcValue;
}

-(NSString*)vfcode {
	NSString* value = self.context[C_VFCODE];
	return value;
}


@end


