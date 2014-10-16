//
//  QUICKSTARTRegisterRequest.h
//  用户注册.请求报文
//
//  Created by 代码生成器1.0.
//
#import "MessageRequest.h"

@interface QUICKSTARTRegisterRequest : MessageRequest
-(void)setEmail:(NSString*)value;
-(NSString*)email;
-(void)setIdcardno:(NSString*)value;
-(NSString*)idcardno;
-(void)setPassword:(NSString*)value;
-(NSString*)password;
-(void)setPhonenumber:(NSString*)value;
-(NSString*)phonenumber;
-(void)setRealname:(NSString*)value;
-(NSString*)realname;
-(void)setVfcode:(NSString*)value;
-(NSString*)vfcode;

@end


