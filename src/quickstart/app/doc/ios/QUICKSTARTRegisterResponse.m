//
//  QUICKSTARTRegisterResponse.m
//  用户注册.响应报文
//
//  Created by 代码生成器1.0.
//

#import "QUICKSTARTRegisterResponse.h"




@interface QUICKSTARTRegisterResponse ()

@end

@implementation QUICKSTARTRegisterResponse

+(id)responseWithReponseData:(NSData*)responseData {
    QUICKSTARTRegisterResponse* response = [[self alloc] init];
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


