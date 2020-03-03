package com.alphasta.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphasta.commons.result.Tree;
import com.alphasta.commons.utils.ExcelUtils;
import com.alphasta.commons.utils.GetIdUtil;
import com.alphasta.mapper.QuestionMapper;
import com.alphasta.model.Options;
import com.alphasta.model.OptionsExample;
import com.alphasta.model.Question;
import com.alphasta.model.QuestionExample;
import com.alphasta.model.QuestionExample.Criteria;
import com.alphasta.service.OptionService;
import com.alphasta.service.QuestionService;
import com.alphasta.service.QuestionnaireService;

@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private OptionService optionService;
	@Autowired
	private QuestionnaireService qnaireService;

	@Override
	public int countByExample(QuestionExample example) {
		// TODO Auto-generated method stub
		return questionMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(QuestionExample example) {
		List<Question> list = this.selectByExample(example);
		for (Question q : list) {
			OptionsExample ex = new OptionsExample();
			ex.createCriteria().andQuestionEqualTo(q.getId());
			optionService.deleteByExample(ex);
		}
		return questionMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return questionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Question record) {
		// TODO Auto-generated method stub
		return questionMapper.insert(record);
	}

	@Override
	public int insertSelective(Question record) {
		// TODO Auto-generated method stub
		return questionMapper.insertSelective(record);
	}

	@Override
	public List<Question> selectByExample(QuestionExample example) {
		// TODO Auto-generated method stub
		return questionMapper.selectByExample(example);
	}

	@Override
	public Question selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return questionMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(Question record, QuestionExample example) {
		// TODO Auto-generated method stub
		return questionMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(Question record, QuestionExample example) {
		// TODO Auto-generated method stub
		return questionMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(Question record) {
		// TODO Auto-generated method stub
		return questionMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Question record) {
		// TODO Auto-generated method stub
		return questionMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Tree> getAllOption(String naireid) {
		List<Tree> trees = new ArrayList<Tree>();
		int ssid = 1;
		QuestionExample example = new QuestionExample();
		example.setOrderByClause("cast(answer as SIGNED INTEGER) asc");
		example.createCriteria().andQuestionnaireEqualTo(naireid);
		List<Question> questions = questionMapper.selectByExample(example);
		for (Question q : questions) {
			Tree qTree = new Tree();
			qTree.setId(ssid++);
			qTree.setText(q.getTopic());
			Map<String, Object> attrs = new HashMap<String, Object>();
			attrs.put("type", "0");
			attrs.put("attr1", q.getAnswer());
			attrs.put("attr2", q.getScore());
			attrs.put("attr3", "");
			attrs.put("attrid", q.getId());
			qTree.setIconCls("icon-company");

			qTree.setAttributes(attrs);
			OptionsExample oex = new OptionsExample();
			oex.createCriteria().andQuestionEqualTo(q.getId());
			List<Options> options = optionService.selectByExample(oex);
			List<Tree> opTree = new ArrayList<Tree>();
			if (options != null) {
				for (Options op : options) {
					Map<String, Object> map = new HashMap<String, Object>();
					Tree opT = new Tree();
					opT.setId(ssid++);
					opT.setText(op.getContent());
					opT.setIconCls("icon-company");
					opT.setPid(qTree.getId().toString());
					map.put("type", "1");
					map.put("attr1", op.getNum());
					map.put("attr2", "");
					map.put("attr3", op.getIsanswer());
					map.put("attrid", op.getId());
					opT.setAttributes(map);
					opTree.add(opT);
				}
				qTree.setChildren(opTree);
			} else {
				qTree.setState("closed");
			}
			trees.add(qTree);
		}
		return trees;
	}

	public String importXls(File file, String questionnaire) {
		ExcelUtils excelUtils = new ExcelUtils();
		Map<Integer, String> content = new HashMap<Integer, String>();
		try {
			content = excelUtils.readExcelContent(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
		} catch (OfficeXmlFileException xls) {
			return "请使用xls格式";
		}
		Set<Integer> rowsIndex = content.keySet();
		if (content.isEmpty() || rowsIndex.size() < 3)
			return "EXCEL为空";
		QuestionExample example = new QuestionExample();
		Criteria c = example.createCriteria();
		c.andQuestionnaireEqualTo(questionnaire);
		List<Question> questionsNow = questionMapper.selectByExample(example);
		StringBuffer indexNow = new StringBuffer("_");
		for (Question q : questionsNow) {
			indexNow.append(q.getAnswer() + "_");
		}
		int rowid = 0 + questionsNow.size() - 2;
		for (Integer rowIndex : rowsIndex) {
			rowid++;
			if (rowIndex > 1) {
				String row = content.get(rowIndex);
				String[] cells = row.split("_");
				if (StringUtils.isEmpty(cells[14])
						|| StringUtils.isEmpty(cells[2])
						|| StringUtils.isEmpty(cells[3])
						|| StringUtils.isEmpty(cells[1]))
					return "第" + (rowid + 2) + "行:数据不完整，请修改后重试";
				Question question = new Question();
				question.setId(GetIdUtil.getId());
				question.setAnswer(String.valueOf(rowid));
				question.setMethod(1);
				question.setQuestionnaire(questionnaire);
				question.setType(1);
				question.setTopic(cells[1]);
				question.setScore(cells[2]);
				questionMapper.insert(question);
				String answer = cells[14].toUpperCase();
				for (int i = 3; i < 14; i++) {
					if (StringUtils.isNotEmpty(cells[i])) {
						Options option = new Options();
						option.setId(GetIdUtil.getId());
						option.setContent(cells[i]);
						option.setQuestion(question.getId());
						String num = String.valueOf((char) (i + 94))
								.toUpperCase();
						option.setNum(num);
						option.setIsanswer(answer.contains(num) ? 1 : 0);
						optionService.insert(option);
					} else {
						break;
					}
				}

			}
		}
		return "导入成功";
	}

	public static void main(String[] args) {
		System.out.println((char) 97);

	}

	@Override
	public List<Question> findByQuestionnate(Map<String, String> map) {
		// TODO Auto-generated method stub
		return questionMapper.findByQuestionnate(map);
	}
}
