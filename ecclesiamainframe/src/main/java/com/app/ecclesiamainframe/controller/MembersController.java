/**
 * 
 */
package com.app.ecclesiamainframe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.SessionAttributes;

import com.app.ecclesiamainframe.dao.MembersDao;
import com.app.ecclesiamainframe.entity.Members;
import com.app.ecclesiamainframe.service.MembersService;

/**
 * @author Harry
 *
 */
@RestController
@RequestMapping("/api/member")
public class MembersController {
	Members mem = new Members();
	
	@Autowired
	private MembersService membersService;
	
	@SuppressWarnings("unused")
	@Autowired
	private MembersDao membersDao;
	
	 @PostMapping(path= "/save", consumes = "application/json", produces = "application/json")
	public Members save(@RequestBody Members member) {
		return membersService.saveMember(member);
	}
	
	 @PutMapping(path= "/update")
	public Members update(@RequestBody Members member) {
		return membersService.updateMembers(member);
	}
	
	 @GetMapping(path="/list")
	 @ResponseBody
	public List<Members> getMembers(){
		 List<Members> resp = membersService.getMembers();
		 return resp;
	}
	
	@GetMapping(path="/{memberId}", produces = "application/json")
	public Optional<Members> getMember(@PathVariable(name = "memberId") Long memberId) {
		return membersService.getMember(memberId);
	}
	
	@GetMapping(path="name/{memberName}", produces = "application/json")
	public List<Members> getMember(@PathVariable(name = "memberName") String memberName) {	
		return membersService.findByMembername(memberName);
	}
	
	
	@DeleteMapping("/delete/{memberId}")
	public void deleteMember(@PathVariable(name = "memberId") Long memberId) {
		membersService.deleteMember(memberId);
	}
	
}