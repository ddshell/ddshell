//
//  QUICKSTARTSubmitLoanResponse.m
//  提交借款申请.响应报文
//
//  Created by 代码生成器1.0.
//

#import "QUICKSTARTSubmitLoanResponse.h"




@interface QUICKSTARTSubmitLoanResponse ()

@end

@implementation QUICKSTARTSubmitLoanResponse

+(id)responseWithReponseData:(NSData*)responseData {
    QUICKSTARTSubmitLoanResponse* response = [[self alloc] init];
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


