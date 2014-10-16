//
//  QUICKSTARTGetPasswordByEmailResponse.m
//  邮箱找回密码.响应报文
//
//  Created by 代码生成器1.0.
//

#import "QUICKSTARTGetPasswordByEmailResponse.h"




@interface QUICKSTARTGetPasswordByEmailResponse ()

@end

@implementation QUICKSTARTGetPasswordByEmailResponse

+(id)responseWithReponseData:(NSData*)responseData {
    QUICKSTARTGetPasswordByEmailResponse* response = [[self alloc] init];
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


