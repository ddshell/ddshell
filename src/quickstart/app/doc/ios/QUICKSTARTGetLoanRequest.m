//
//  QUICKSTARTGetLoanRequest.m
//  获取借款详情.请求报文
//
//  Created by 代码生成器1.0.
//

#import "QUICKSTARTGetLoanRequest.h"
#import "QUICKSTARTGetLoanResponse.h"




@implementation QUICKSTARTGetLoanRequest

+(Class)responseClass {    
    return [QUICKSTARTGetLoanResponse class];
}

+(NSString*)RA_type {
    return @"getLoan";
}

-(id)init {
    self = [super init];
    if ( self ) {
    }
    return self;
}



@end


