//
//  QUICKSTARTLoginResponse.m
//  用户登录.响应报文
//
//  Created by 代码生成器1.0.
//

#import "QUICKSTARTLoginResponse.h"


#define C_IDCARDNO      @"idcardno"
#define C_PHONENUMBER      @"phonenumber"
#define C_REALNAME      @"realname"


@interface QUICKSTARTLoginResponse ()

@end

@implementation QUICKSTARTLoginResponse

+(id)responseWithReponseData:(NSData*)responseData {
    QUICKSTARTLoginResponse* response = [[self alloc] init];
    [response fromString: [[NSString alloc] initWithData: responseData encoding: NSUTF8StringEncoding]];
    return response;
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


@end


