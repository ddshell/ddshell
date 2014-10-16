//
//  QUICKSTARTGetRegisterVfcodeResponse.m
//  获取注册验证码.响应报文
//
//  Created by 代码生成器1.0.
//

#import "QUICKSTARTGetRegisterVfcodeResponse.h"




@interface QUICKSTARTGetRegisterVfcodeResponse ()

@end

@implementation QUICKSTARTGetRegisterVfcodeResponse

+(id)responseWithReponseData:(NSData*)responseData {
    QUICKSTARTGetRegisterVfcodeResponse* response = [[self alloc] init];
    [response fromString: [[NSString alloc] initWithData: responseData encoding: NSUTF8StringEncoding]];
    return response;
}

-(id)init {
    self = [super init];
    if ( self ) {
    }
    return self;
}



@end


