//
//  QUICKSTARTGetPasswordByPhoneResponse.m
//  手机找回密码.响应报文
//
//  Created by 代码生成器1.0.
//

#import "QUICKSTARTGetPasswordByPhoneResponse.h"




@interface QUICKSTARTGetPasswordByPhoneResponse ()

@end

@implementation QUICKSTARTGetPasswordByPhoneResponse

+(id)responseWithReponseData:(NSData*)responseData {
    QUICKSTARTGetPasswordByPhoneResponse* response = [[self alloc] init];
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


